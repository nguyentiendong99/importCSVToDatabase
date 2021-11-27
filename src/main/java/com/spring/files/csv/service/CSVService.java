package com.spring.files.csv.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.spring.files.csv.repository.OutlierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.files.csv.helper.CSVHelper;
import com.spring.files.csv.model.Outlier;

@Service
public class CSVService {
  @Autowired
  OutlierRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Outlier> outliers = CSVHelper.csvToTutorials(file.getInputStream());
      repository.saveAll(outliers);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Outlier> outliers = repository.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(outliers);
    return in;
  }

  public List<Outlier> getAllTutorials() {
    return repository.findAll();
  }
}
