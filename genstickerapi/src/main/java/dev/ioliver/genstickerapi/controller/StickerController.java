package dev.ioliver.genstickerapi.controller;

import dev.ioliver.genstickerapi.service.StickerService.StickerSchemaDto;
import dev.ioliver.genstickerapi.service.StickerService.StickerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("generate")
public class StickerController {

  @Autowired
  StickerService stickerService;

  @PostMapping
  public ResponseEntity<byte[]> generateSticker(@ModelAttribute @Valid StickerSchemaDto sticker) throws IOException {
    BufferedImage bufferedImage = stickerService.generate(sticker);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", os);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(os.toByteArray());
  }

}
