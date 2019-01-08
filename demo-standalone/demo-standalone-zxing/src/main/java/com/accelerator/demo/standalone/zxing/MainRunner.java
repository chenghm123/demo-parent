package com.accelerator.demo.standalone.zxing;

import com.beust.jcommander.internal.Lists;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.google.zxing.BarcodeFormat.*;

public class MainRunner {

    public static final Map<DecodeHintType, Object> HINTS = new EnumMap<>(DecodeHintType.class);

    static {
        List<BarcodeFormat> allFormats = Lists.newArrayList(
                AZTEC,
                CODABAR,
                CODE_39,
                CODE_93,
                CODE_128,
                DATA_MATRIX,
                EAN_8,
                EAN_13,
                ITF,
                MAXICODE,
                PDF_417,
                QR_CODE,
                RSS_14,
                RSS_EXPANDED,
                UPC_A,
                UPC_E,
                UPC_EAN_EXTENSION
        );
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, allFormats);

        // 优化精度
        HINTS.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        // 复杂模式
        HINTS.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        // 解码设置编码方式为：UTF-8
        HINTS.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
    }

    public static void main(String[] args) {
        File file = new File("C:/Users/Accelerator/Desktop/c.jpg");
        System.out.println(file.exists());
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Result result = new MultiFormatReader().decode(binaryBitmap, HINTS);// 对图像进行解码
            System.out.println(result.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
