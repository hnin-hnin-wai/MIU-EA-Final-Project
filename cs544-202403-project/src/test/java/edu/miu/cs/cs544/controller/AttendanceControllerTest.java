package edu.miu.cs.cs544.controller;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.controller.AttendanceController;
import edu.miu.cs.cs544.service.AttendanceService;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendanceControllerTest {

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetScanRecordsByScannerCode_Success() {
        // Given
        int scannerCode = 123;
        List<AttendancePayload> records = new ArrayList<>();
        when(attendanceService.findByScannerCode(scannerCode)).thenReturn(records);

        // When
        ResponseEntity<?> responseEntity = attendanceController.getScanRecordsByScannerCode(scannerCode);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(records, responseEntity.getBody());
    }

    @Test
    void testGetScanRecordsByScannerCode_NoRecordsFound() {
        // Given
        int scannerCode = 123;
        when(attendanceService.findByScannerCode(scannerCode)).thenReturn(new ArrayList<>());

        // When/Then
        assertThrows(ResourceNotFoundException.class, () -> {
            attendanceController.getScanRecordsByScannerCode(scannerCode);
        });
    }

    @Test
    void testSaveScanRecords() {
        // Given
        int scannerCode = 123;
        AttendancePayload attendancePayload = new AttendancePayload();

        // When
        attendanceController.saveScanRecords(scannerCode, attendancePayload);

        // Then
        verify(attendanceService, times(1)).saveAttendance(scannerCode, attendancePayload);
    }

    @Test
    void testDeleteScanRecords() {
        // Given
        int scannerCode = 123;
        int recordId = 456;

        // When
        attendanceController.deleteScanRecords(scannerCode, recordId);

        // Then
        verify(attendanceService, times(1)).deleteAttendance(scannerCode, recordId);
    }

    @Test
    void testUpdateScanRecords() {
        // Given
        int scannerCode = 123;
        int recordId = 456;
        AttendancePayload attendancePayload = new AttendancePayload();

        // When
        AttendancePayload result = attendanceController.updateScanRecords(scannerCode, recordId, attendancePayload);

        // Then
        assertNotNull(result);

    }

}