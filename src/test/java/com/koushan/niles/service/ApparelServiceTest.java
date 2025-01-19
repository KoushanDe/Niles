package com.koushan.niles.service;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.repository.ApparelRepository;
import com.koushan.niles.service.impl.ApparelServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApparelServiceTest {

    @Mock
    private ApparelRepository apparelRepository;

    @Mock
    private CloudinaryService cloudinaryService;

    @InjectMocks
    private ApparelServiceImpl apparelService;

    @Test
    void testProcessApparelCreationSuccess() throws IOException {
        // Arrange
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(file.getBytes()).thenReturn(new byte[]{1, 2, 3});

        Map<String, String> uploadResult = new HashMap<>();
        uploadResult.put("url", "http://example.com/image.jpg");
        when(cloudinaryService.upload(any(), any())).thenReturn(uploadResult);

        ApparelDto dto = new ApparelDto();
        dto.setPrice(100.0);
        when(apparelRepository.save(any(Apparel.class))).thenReturn(new Apparel());

        ModelAndView result = apparelService.processApparelCreation(dto, file);

        assertEquals("redirect:/admin?apparelSuccess", result.getViewName());
        verify(apparelRepository, times(1)).save(any(Apparel.class));
        verify(cloudinaryService, times(1)).upload(any(), any());
    }

    @Test
    void testProcessApparelCreationEmptyFile() {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(true);

        ApparelDto dto = new ApparelDto();

        ModelAndView result = apparelService.processApparelCreation(dto, file);

        assertEquals("redirect:/", result.getViewName());
        verifyNoInteractions(apparelRepository);
        verifyNoInteractions(cloudinaryService);
    }

}
