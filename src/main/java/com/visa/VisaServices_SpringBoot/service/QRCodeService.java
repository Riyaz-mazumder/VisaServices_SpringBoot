package com.visa.VisaServices_SpringBoot.service;

//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.WriterException;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Service;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;


import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.imgscalr.Scalr;





@Service
public class QRCodeService {


    private final ResourceLoader resourceLoader;

    @Autowired
    public QRCodeService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public byte[] generateQRCodeWithLogo(String qrData, String logoPath) throws IOException, WriterException {
        // Generate QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 250, 250);

        // Create buffered image for the QR code
        BufferedImage qrCodeImage = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        qrCodeImage.createGraphics();

        Color qrCodeColor = new Color(46, 83, 150); // RGB values: (46, 83, 150)
        for (int x = 0; x < 250; x++) {
            for (int y = 0; y < 250; y++) {
                qrCodeImage.setRGB(x, y, bitMatrix.get(x, y) ? qrCodeColor.getRGB() : Color.WHITE.getRGB());
            }
        }
//
//        // Load the logo image
//        BufferedImage logoImage = ImageIO.read(getClass().getResourceAsStream(logoPath));


        // Load the logo image using ResourceLoader
        Resource resource = resourceLoader.getResource(logoPath);
        BufferedImage logoImage = ImageIO.read(resource.getInputStream());

        // Resize the logo image
        logoImage = Scalr.resize(logoImage, 60);


        // Scale down the logo image to fit the QR code
        int logoWidth = Math.min(logoImage.getWidth(), 60);
        int logoHeight = Math.min(logoImage.getHeight(), 60);
        BufferedImage scaledLogoImage = new BufferedImage(logoWidth, logoHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = scaledLogoImage.createGraphics();
        graphics.drawImage(logoImage, 0, 0, logoWidth, logoHeight, null);
        graphics.dispose();

        // Merge the QR code and logo image
        int qrCodeSize = 250;
        BufferedImage combinedImage = new BufferedImage(qrCodeSize, qrCodeSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D combinedGraphics = combinedImage.createGraphics();
        combinedGraphics.drawImage(qrCodeImage, 0, 0, null);

        int logoX = (qrCodeSize - logoWidth) / 2;
        int logoY = (qrCodeSize - logoHeight) / 2;
        combinedGraphics.drawImage(scaledLogoImage, logoX, logoY, null);
        combinedGraphics.dispose();

        // Convert the combined image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(combinedImage, "png", baos);
        return baos.toByteArray();
    }
}
