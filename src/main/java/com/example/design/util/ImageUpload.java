package com.example.design.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import reactor.event.Event;
import reactor.function.Function;

/**
 * Created by lxh on 16/4/28.
 */
public class ImageUpload implements Function<Event<Path>, Path> {

  private static final ImageObserver DUMMY_OBSERVER = (img, info, x, y, width, height) -> true;

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final int maxLongSide;

  public ImageUpload(int maxLongSide) {
    this.maxLongSide = maxLongSide;
  }

  public Path apply(Event<Path> pathEvent) {
    try {
      Path srcPath = pathEvent.getData();
      Path thumbnailPath = Files.createTempFile("thumbnail", ".jpg").toAbsolutePath();
      BufferedImage imgIn = ImageIO.read(srcPath.toFile());

      double scale;
      if (imgIn.getWidth() >= imgIn.getHeight()) {
        // horizontal or square image
        scale = Math.min(maxLongSide, imgIn.getWidth()) / (double) imgIn.getWidth();
      } else {
        // vertical image
        scale = Math.min(maxLongSide, imgIn.getHeight()) / (double) imgIn.getHeight();
      }

      BufferedImage thumbnailOut = new BufferedImage((int) (scale * imgIn.getWidth()),
              (int) (scale * imgIn.getHeight()),
              imgIn.getType());
      Graphics2D g = thumbnailOut.createGraphics();

      AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
      g.drawImage(imgIn, transform, DUMMY_OBSERVER);
      ImageIO.write(thumbnailOut, "jpeg", thumbnailPath.toFile());

      log.info("Image thumbnail now at: {}", thumbnailPath);

      return thumbnailPath;
    } catch (Exception e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
