// package com.example.rest.service;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.awt.image.BufferedImage;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.io.PrintStream;
// import java.util.Arrays;
// import java.util.concurrent.TimeUnit;

// import javax.imageio.ImageIO;
// import javax.swing.ImageIcon;
// import SecuGen.FDxSDKPro.jni.*; 

// @RestController
// public class SecugenController {

//     public static JSGFPLib sgfplib = new JSGFPLib();
// 	public static long check ;
// 	public static long err ;
// 	 public static SGDeviceInfoParam deviceInfo;
//      private byte[] regMin1 = new byte[400];


//     @GetMapping("/fingerprint")
//     public Secugen fingerprint()
//     {
//         System.out.println("Starting here okkkkkkkkkkkkkkkkkkkkk");
//         sgfplib.Init(SGFDxDeviceName.SG_DEV_AUTO);
	   
// 	   check = sgfplib.OpenDevice(2);
//        System.out.println("open device" + " " + check);
//        deviceInfo = new SGDeviceInfoParam();
        
//        deviceInfo = new SGDeviceInfoParam();
//        check = sgfplib.GetDeviceInfo(deviceInfo);
//       System.out.println(check );
//       System.out.println("\tdeviceInfo.DeviceSN:    [" + new String(deviceInfo.deviceSN()) + "]");
//       System.out.println("\tdeviceInfo.Brightness:  [" + deviceInfo.brightness + "]");
//       System.out.println("\tdeviceInfo.ComPort:     [" + deviceInfo.comPort + "]");
//       System.out.println("\tdeviceInfo.ComSpeed:    [" + deviceInfo.comSpeed + "]");
//       System.out.println("\tdeviceInfo.Contrast:    [" + deviceInfo.contrast + "]");
//       System.out.println("\tdeviceInfo.DeviceID:    [" + deviceInfo.deviceID + "]");
//       System.out.println("\tdeviceInfo.FWVersion:   [" + deviceInfo.FWVersion + "]");
//       System.out.println("\tdeviceInfo.Gain:        [" + deviceInfo.gain + "]");
//       System.out.println("\tdeviceInfo.ImageDPI:    [" + deviceInfo.imageDPI + "]");
//       System.out.println("\tdeviceInfo.ImageHeight: [" + deviceInfo.imageHeight + "]");
//       System.out.println("\tdeviceInfo.ImageWidth:  [" + deviceInfo.imageWidth + "]");

//        byte[] buffer = new byte[deviceInfo.imageWidth*deviceInfo.imageHeight];
//        long timeout = 10000;
//        long quality = 80;
//        int[] img_qlt = new int[80];

//        System.out.println("template image" + buffer);
       
//        if(sgfplib.GetImageEx(buffer, timeout, 1, quality) ==SGFDxErrorCode.SGFDX_ERROR_NONE)
//        {


//             sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378);

//         //    err = sgfplib.GetImageQuality(deviceInfo.imageHeight, deviceInfo.imageWidth, buffer, img_qlty);
//            SGFingerInfo fingerInfo = new SGFingerInfo();
//            fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
//            fingerInfo.ImageQuality = img_qlt[0];
//            fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
//            fingerInfo.ViewNumber = 1;

//             // sgfplib.GetMaxTemplateSize(400);

//             err = sgfplib.CreateTemplate(fingerInfo, buffer, regMin1);


//             System.out.println("template created" + err);
            
          

//            byte[][] buffer2D = new byte[deviceInfo.imageHeight][deviceInfo.imageWidth];
        
         

//            for(int i=0;i<deviceInfo.imageHeight;i++) {
//                for(int j=0;j<deviceInfo.imageWidth;j++) {
//                    buffer2D[i][j]=buffer[i*deviceInfo.imageWidth+j];
//                 //    System.out.print(buffer2D[i][j]+" ,");
//                    //System.out.println(i+" ,"+j+" "+(i*deviceInfo.imageWidth+j));
//                }
//            }
//            writeImage(buffer2D);
//            sgfplib.Close();
//    }

//         return new Secugen(true);
    
    
// }




// public  void writeImage(byte[][] img) {
//     String path = "C:\\Users\\achar\\Downloads\\secuimg\\ok.png";
//     BufferedImage image = new BufferedImage(img.length, img[0].length, BufferedImage.TYPE_BYTE_GRAY);
//     for (int x = 0; x < img.length; x++) {
//         for (int y = 0; y <img[0].length; y++) {
//             image.setRGB(x, y, img[x][y]);
//         }
//     }

//     File ImageFile = new File(path);
//     try {
//         ImageIO.write(image, "png", ImageFile);
//     } catch (IOException e) {
//         e.printStackTrace();
//     }
// }

// }
