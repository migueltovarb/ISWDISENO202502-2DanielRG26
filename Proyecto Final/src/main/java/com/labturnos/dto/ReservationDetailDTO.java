package com.labturnos.dto;

import com.labturnos.domain.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDetailDTO {
  private String id;
  private LocalDate date;
  private LocalTime startTime;
  private LocalTime endTime;
  private ReservationStatus status;
  private String labId;
  private String labCode;
  private String labName;
  private String equipmentId;
  private String equipmentIdentifier;
  private String equipmentType;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public ReservationStatus getStatus() {
    return status;
  }

  public void setStatus(ReservationStatus status) {
    this.status = status;
  }

  public String getLabId() {
    return labId;
  }

  public void setLabId(String labId) {
    this.labId = labId;
  }

  public String getLabCode() {
    return labCode;
  }

  public void setLabCode(String labCode) {
    this.labCode = labCode;
  }

  public String getLabName() {
    return labName;
  }

  public void setLabName(String labName) {
    this.labName = labName;
  }

  public String getEquipmentId() {
    return equipmentId;
  }

  public void setEquipmentId(String equipmentId) {
    this.equipmentId = equipmentId;
  }

  public String getEquipmentIdentifier() {
    return equipmentIdentifier;
  }

  public void setEquipmentIdentifier(String equipmentIdentifier) {
    this.equipmentIdentifier = equipmentIdentifier;
  }

  public String getEquipmentType() {
    return equipmentType;
  }

  public void setEquipmentType(String equipmentType) {
    this.equipmentType = equipmentType;
  }
}
