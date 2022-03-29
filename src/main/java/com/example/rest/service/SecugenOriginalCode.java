// package com.example.rest.service;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.awt.image.BufferedImage;

// import java.io.*;
// import javax.imageio.ImageIO;
// import SecuGen.FDxSDKPro.jni.*; 

// @RestController
// public class SecugenOriginalCode {

//     // private static final String DRIVER_CLASS = " SecuGen.FDxSDKPro.jni.JSGFPLib";
//     // public static JSGFPLib sgfplib = new JSGFPLib();
// 	public static long check ;
// 	public static long err ;
//     byte[] imageBuffer1;
//     byte[] imageBuffer2;
//     byte[] SG400minutiaeBuffer1;
//     byte[] ANSIminutiaeBuffer1;
//     byte[] ISOminutiaeBuffer1;
//     byte[] SG400minutiaeBuffer2;
//     byte[] ANSIminutiaeBuffer2;
//     byte[] ISOminutiaeBuffer2;
//     FileOutputStream fout = null;
//     PrintStream fp = null;

// 	//  public static SGDeviceInfoParam deviceInfo;
//     //  private byte[] regMin1 = new byte[400];


//     @GetMapping("/fingerprint")
//     public Secugen fingerprint()
//     {
//         JSGFPLib sgfplib = new JSGFPLib();
//         if((sgfplib !=null) &&(sgfplib.jniLoadStatus!= SGFDxErrorCode.SGFDX_ERROR_JNI_DLLLOAD_FAILED))
//         {
//             System.out.println(sgfplib);
//         }
//         else{
//             return new Secugen("Cannot find device");
//         }

//         // Initializing secugen
//        err= sgfplib.Init(SGFDxDeviceName.SG_DEV_AUTO);
//        System.out.println("Initializing secugen : [" + err + "]");

//                ///////////////////////////////////////////////
//         // GetMinexVersion()
//         int[] extractorVersion = new int[1];
//         int[] matcherVersion = new int[1];
//         System.out.println("Call GetMinexVersion()");
//         err = sgfplib.GetMinexVersion(extractorVersion, matcherVersion);
//         System.out.println("GetMinexVersion returned : [" + err + "]");
//         System.out.println("Extractor version : [" + extractorVersion[0] + "]");
//         System.out.println("Matcher version : [" + matcherVersion[0] + "]");

//        // Opening device
//        System.out.println("Opening secugen device");
// 	//    err = sgfplib.OpenDevice(SGPPPortAddr.AUTO_DETECT);
// 	   err = sgfplib.OpenDevice(2);
//        System.out.println("OpenDevice returned : [" + err + "]");

//        SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();
//        err = sgfplib.GetDeviceInfo(deviceInfo);
//       System.out.println(err );
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

//       //Turning LED On to let user know to put finger
//     //   err = sgfplib.SetLedOn(true);

//     int[] quality = new int[1];
//     int[] maxSize = new int[1];
//     int[] size = new int[1];
//     SGFingerInfo fingerInfo = new SGFingerInfo();
//     fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
//     fingerInfo.ImageQuality = quality[0];
//     fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
//     fingerInfo.ViewNumber = 1;

//     //Capturing first Image
//     err =sgfplib.SetLedOn(true);
//     imageBuffer1 = new byte[deviceInfo.imageHeight*deviceInfo.imageWidth];
//     try{
//         err = sgfplib.GetImage(imageBuffer1);
//         if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 err = sgfplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1, quality);
//                 System.out.println("GetImageQuality returned : [" + err + "]");
//                 System.out.println("Image Quality is : [" + quality[0] + "]");

//                 //create image png
//                 byte[][] buffer2D = new byte[deviceInfo.imageHeight][deviceInfo.imageWidth];
//                 String imageName = "firstImage.png";
//                 for(int i=0;i<deviceInfo.imageHeight;i++) {
//                     for(int j=0;j<deviceInfo.imageWidth;j++) {
//                         buffer2D[i][j]=imageBuffer1[i*deviceInfo.imageWidth+j];
//                         // System.out.print(buffer2D[i][j]+" ,");
//                         //System.out.println(i+" ,"+j+" "+(i*deviceInfo.imageWidth+j));
//                     }
//                     // System.out.println();
//                 }
//                 writeImage(buffer2D,imageName);
//                 fout = new FileOutputStream("sample" + "1.raw");
//                 fp = new PrintStream(fout);
//                 fp.write(imageBuffer1,0, imageBuffer1.length);
//                 fp.close();
//                 fout.close();
//                 fp = null;
//                 fout = null;
//             }
//             else
//             {
//                 System.out.println("ERROR: Fingerprint image capture failed for sample1.");
//                 return new Secugen("Error capturing image");
//             }
//     }
//     catch(IOException e)
//     {
//         System.out.println("Exception reading keyboard : " + e);

//     }

//     err =sgfplib.SetLedOn(false);

//      ///////////////////////////////////////////////
//     // Set Template format ISO19794
//     System.out.println("Call SetTemplateFormat(ISO19794)");
//     err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794);
//     System.out.println("SetTemplateFormat returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // Get Max Template Size for ISO19794
//         System.out.println("Call GetMaxTemplateSize()");
//         err = sgfplib.GetMaxTemplateSize(maxSize);
//         System.out.println("GetMaxTemplateSize returned : [" + err + "]");
//         System.out.println("Max ISO19794 Template Size is : [" + maxSize[0] + "]");
//         ///////////////////////////////////////////////
//         // Greate ISO19794 Template for Finger1
//         ISOminutiaeBuffer1 = new byte[maxSize[0]];
//         System.out.println("Call CreateTemplate()");
//         err = sgfplib.CreateTemplate(fingerInfo, imageBuffer1, ISOminutiaeBuffer1);
//         System.out.println("CreateTemplate returned : [" + err + "]");
//         err = sgfplib.GetTemplateSize(ISOminutiaeBuffer1, size);
//         System.out.println("GetTemplateSize returned : [" + err + "]");
//         System.out.println("ISO19794 Template Size is : [" + size[0] + "]");
//         try
//         {
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 fout = new FileOutputStream("sample" +"1.iso19794");
//                 fp = new PrintStream(fout);
//                 fp.write(ISOminutiaeBuffer1,0, size[0]);
//                 fp.close();
//                 fout.close();
//                 fp = null;
//                 fout = null;
//             }
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception writing minutiae file : " + e);
//         }

// //////////////////////////////////////////////
// //capturing finger print again
// System.out.println("Call SetLedOn(true)");
// err =sgfplib.SetLedOn(true);
// imageBuffer2 = new byte[deviceInfo.imageHeight*deviceInfo.imageWidth];
// try{
//     err = sgfplib.GetImage(imageBuffer2);
//     if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//     {

//         err = sgfplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer2, quality);
//         System.out.println("GetImageQuality returned : [" + err + "]");
//         System.out.println("Image Quality is : [" + quality[0] + "]");

//         //create image png
//         byte[][] buffer2D = new byte[deviceInfo.imageHeight][deviceInfo.imageWidth];
//         String imageName = "secondImage.png";
//         for(int i=0;i<deviceInfo.imageHeight;i++) {
//             for(int j=0;j<deviceInfo.imageWidth;j++) {
//                 buffer2D[i][j]=imageBuffer2[i*deviceInfo.imageWidth+j];
//                 // System.out.print(buffer2D[i][j]+" ,");
//                 //System.out.println(i+" ,"+j+" "+(i*deviceInfo.imageWidth+j));
//             }
//             // System.out.println();
//         }
//         writeImage(buffer2D,imageName);
//         fout = new FileOutputStream("sample" + "2.raw");
//         fp = new PrintStream(fout);
//         fp.write(imageBuffer2,0, imageBuffer2.length);
//         fp.close();
//         fout.close();
//         fp = null;
//         fout = null;
//     }
//     else
//     {
//         System.out.println("ERROR: Fingerprint image capture failed for sample2.");
//         return new Secugen("Error capturing image"); //Cannot continue test if image not captured
//     }

// }
// catch(IOException e){
//     System.out.println("Exception reading keyboard : " + e);

// }

//         ///////////////////////////////////////////////
//         // Set Template format ISO19794
//         System.out.println("--------");
//         System.out.println("Call SetTemplateFormat(ISO19794)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // Get Max Template Size for ISO19794
//         System.out.println("Call GetMaxTemplateSize()");
//         err = sgfplib.GetMaxTemplateSize(maxSize);
//         System.out.println("GetMaxTemplateSize returned : [" + err + "]");
//         System.out.println("Max ISO19794 Template Size is : [" + maxSize[0] + "]");

//         ///////////////////////////////////////////////
//         // Greate ISO19794 Template for Finger 2
//         ISOminutiaeBuffer2 = new byte[maxSize[0]];
//         System.out.println("Call CreateTemplate()");
//         err = sgfplib.CreateTemplate(fingerInfo, imageBuffer2, ISOminutiaeBuffer2);
//         System.out.println("CreateTemplate returned : [" + err + "]");
//         err = sgfplib.GetTemplateSize(ISOminutiaeBuffer2, size);
//         System.out.println("GetTemplateSize returned : [" + err + "]");
//         System.out.println("ISO19794 Template Size is : [" + size[0] + "]");
//         try
//         {
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 fout = new FileOutputStream("sample" +"2.iso19794");
//                 fp = new PrintStream(fout);
//                 fp.write(ISOminutiaeBuffer2,0, size[0]);
//                 fp.close();
//                 fout.close();
//                 fp = null;
//                 fout = null;
//             }
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception writing minutiae file : " + e);
//         }
//         boolean[] matched = new boolean[1];
//         int[] score = new int[1];

//         ///////////////////////////////////
//         //Match ISO19794 Templates
//         System.out.println("--------");
//         matched[0] = false;
//         score[0] = 0;
//         System.out.println("Call SetTemplateFormat(ISO19794)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");
//         System.out.println("Call MatchIsoTemplates()");
//         err = sgfplib.MatchIsoTemplate(ISOminutiaeBuffer1, 0, ISOminutiaeBuffer2, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchISOTemplates returned : [" + err + "]");
//         System.out.println("ISO-1 <> ISO-2 Match Result : [" + matched[0] + "]");
//         System.out.println("Call GetIsoMatchingScore()");
//         err = sgfplib.GetIsoMatchingScore(ISOminutiaeBuffer1, 0, ISOminutiaeBuffer2, 0, score);
//         System.out.println("GetIsoMatchingScore returned : [" + err + "]");
//         System.out.println("ISO-1  <> ISO-2 Match Score : [" + score[0] + "]");

//                 ///////////////////////////////////
//         //Merge ISO19794 Templates
//         System.out.println("--------");
//         err = sgfplib.GetIsoTemplateSizeAfterMerge(ISOminutiaeBuffer1, ISOminutiaeBuffer2, size);
//         System.out.println("GetIsoTemplateSizeAfterMerge returned : [" + err + "]");
//         System.out.println("ISO-1 + ISO-2 Size : [" + size[0] + "]");
//         byte[] mergedIsoTemplate1 = new byte[size[0]];
//         err = sgfplib.MergeIsoTemplate(ISOminutiaeBuffer1, ISOminutiaeBuffer2, mergedIsoTemplate1);
//         System.out.println("MergeIsoTemplate returned : [" + err + "]");
//         err = sgfplib.MatchIsoTemplate(ISOminutiaeBuffer1, 0, mergedIsoTemplate1, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchIsoTemplate returned : [" + err + "]");
//         System.out.println("ISO-1 <> ISO-MERGED Match Result : [" + matched[0] + "]");
        	

//                 ///////////////////////////////////
//         //View ISO19794 Info
//         System.out.println("--------");
//         SGISOTemplateInfo isoTemplateInfo = new SGISOTemplateInfo();
//         err = sgfplib.GetIsoTemplateInfo(mergedIsoTemplate1, isoTemplateInfo);
//         System.out.println("GetIsoTemplateInfo returned : [" + err + "]");
//         System.out.println("   TotalSamples=" + isoTemplateInfo.TotalSamples);
//         for (int i=0; i<isoTemplateInfo.TotalSamples; ++i){
//         	System.out.println("   Sample[" + i + "].FingerNumber=" + isoTemplateInfo.SampleInfo[i].FingerNumber);
//         	System.out.println("   Sample[" + i + "].ImageQuality=" + isoTemplateInfo.SampleInfo[i].ImageQuality);
//         	System.out.println("   Sample[" + i + "].ImpressionType=" + isoTemplateInfo.SampleInfo[i].ImpressionType);
//         	System.out.println("   Sample[" + i + "].ViewNumber=" + isoTemplateInfo.SampleInfo[i].ViewNumber);
//         }                
        

//         ///////////////////////////////////////////////////////////////////////////////////////////////        
//         //Test NFIQ Score
//         System.out.println("--------");      
//         long nfiqScore = sgfplib.ComputeNFIQ(imageBuffer2, deviceInfo.imageWidth, deviceInfo.imageHeight);
//         System.out.println("NFIQ Score is: " + nfiqScore);       
//         System.out.println("--------");      
      
//         ///////////////////////////////////////////////
//         // CloseDevice()
//         System.out.println("Call CloseDevice()");
//         err = sgfplib.CloseDevice();
//         System.out.println("CloseDevice returned : [" + err + "]");



//         return new Secugen("Finger print registered");
    
    
// }




// public  void writeImage(byte[][] img , String imageName) {
//     String path = imageName;
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
