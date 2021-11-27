package com.spring.files.csv.helper;

import com.spring.files.csv.model.Outlier;
import com.spring.files.csv.util.Constants;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Date", "Price"};

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Outlier> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Outlier> outliers = new ArrayList<Outlier>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Outlier outlier = new Outlier(
              new SimpleDateFormat(Constants.DATE_FORMAT).parse(csvRecord.get("Date")),
              Double.parseDouble(csvRecord.get(("Price"))
            ));

        outliers.add(outlier);
      }

      return outliers;
    } catch (IOException | ParseException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<Outlier> outliers) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Outlier outlier : outliers) {
        List<String> data = Arrays.asList(
              String.valueOf(outlier.getId()),
              new SimpleDateFormat(Constants.DATE_FORMAT).format(outlier.getDate()),
              String.valueOf(outlier.getPrice())
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

}
