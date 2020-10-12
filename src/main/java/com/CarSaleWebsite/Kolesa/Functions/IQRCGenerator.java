package com.CarSaleWebsite.Kolesa.Functions;

import com.CarSaleWebsite.Kolesa.Models.utils.Food;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public interface IQRCGenerator {

     static void generate(Food food) {
         try{
             File file = new File("C:\\Users\\HP\\Desktop\\qr"+food.getID()+".jpg");

             ByteArrayOutputStream byteArrayOutputStream=QRCode.from(food.toString()).to(ImageType.PNG).withSize(200,200).stream();

             FileOutputStream fileOutputStream=new FileOutputStream(file);

             fileOutputStream.write(byteArrayOutputStream.toByteArray());

         }catch (Exception e){
             e.printStackTrace();
         }

    }
}
