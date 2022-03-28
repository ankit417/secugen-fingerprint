// package com.example.rest.service;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.RestTemplate;

// // import java.awt.image.BufferedImage;

// import java.io.*;
// // import java.util.Scanner;

// // import javax.imageio.ImageIO;
// import SecuGen.FDxSDKPro.jni.*; 
// import java.net.URL;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;

// @RestController
// public class VerifyFingerController {
//             // public static JSGFPLib sgfplib = new JSGFPLib();
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

//     @GetMapping("/verify-remote")
//     public VerifyFinger verifyprintremote()
//     {
//         try{
//             // BufferedInputStream in = new BufferedInputStream(new URL("http://192.168.1.77:8848/assets/nas-1/biometric/bio/1/1647252799642.iso19794").openStream());
//             // FileOutputStream fileOutputStream = new FileOutputStream("testing-remote.iso19794");
//             //     byte dataBuffer[] = new byte[1024];
//             //     int bytesRead;
//             //     while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//             //         fileOutputStream.write(dataBuffer, 0, bytesRead);
//             //     }
//             InputStream in = new URL("http://192.168.1.77:8848/assets/nas-1/biometric/bio/1/1647252799642.iso19794").openStream();
//             // Files
//             Files.copy(in, Paths.get("badam.iso19794"), StandardCopyOption.REPLACE_EXISTING);
//             System.out.println("testing testing");
            
//             }
        
//         catch(IOException e)
//         {
//             System.out.println("error downloading file");
//         }
//         JSGFPLib sgfplib = new JSGFPLib();
//         if((sgfplib !=null) &&(sgfplib.jniLoadStatus!= SGFDxErrorCode.SGFDX_ERROR_JNI_DLLLOAD_FAILED))
//         {
//             System.out.println(sgfplib);
//         }
//         else{
//             return new VerifyFinger("Cannot find device");
//         }

//                 // Initializing secugen
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

//                // Opening device
//        System.out.println("Opening secugen device");
//        //    err = sgfplib.OpenDevice(SGPPPortAddr.AUTO_DETECT);
//           err = sgfplib.OpenDevice(2);
//           System.out.println("OpenDevice returned : [" + err + "]");
   
//           SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();
//           err = sgfplib.GetDeviceInfo(deviceInfo);
//          System.out.println(err );
//          System.out.println("\tdeviceInfo.DeviceSN:    [" + new String(deviceInfo.deviceSN()) + "]");
//          System.out.println("\tdeviceInfo.Brightness:  [" + deviceInfo.brightness + "]");
//          System.out.println("\tdeviceInfo.ComPort:     [" + deviceInfo.comPort + "]");
//          System.out.println("\tdeviceInfo.ComSpeed:    [" + deviceInfo.comSpeed + "]");
//          System.out.println("\tdeviceInfo.Contrast:    [" + deviceInfo.contrast + "]");
//          System.out.println("\tdeviceInfo.DeviceID:    [" + deviceInfo.deviceID + "]");
//          System.out.println("\tdeviceInfo.FWVersion:   [" + deviceInfo.FWVersion + "]");
//          System.out.println("\tdeviceInfo.Gain:        [" + deviceInfo.gain + "]");
//          System.out.println("\tdeviceInfo.ImageDPI:    [" + deviceInfo.imageDPI + "]");
//          System.out.println("\tdeviceInfo.ImageHeight: [" + deviceInfo.imageHeight + "]");
//          System.out.println("\tdeviceInfo.ImageWidth:  [" + deviceInfo.imageWidth + "]");

//          int[] quality = new int[1];
//          int[] maxSize = new int[1];
//          int[] size = new int[1];
//          SGFingerInfo fingerInfo = new SGFingerInfo();
//          fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
//          fingerInfo.ImageQuality = quality[0];
//          fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
//          fingerInfo.ViewNumber = 1;

//              //Capturing first Image
//     err =sgfplib.SetLedOn(true);
//     imageBuffer1 = new byte[deviceInfo.imageHeight*deviceInfo.imageWidth];
//     try{
//         err = sgfplib.GetImage(imageBuffer1);
//         if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 err = sgfplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1, quality);
//                 System.out.println("GetImageQuality returned : [" + err + "]");
//                 System.out.println("Image Quality is : [" + quality[0] + "]");
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
//                 return new VerifyFinger("Error capturing finger print image");
//             }
//     }
//     catch(IOException e)
//     {
//         System.out.println("Exception reading keyboard : " + e);

//     }

//     err =sgfplib.SetLedOn(false);
//          ///////////////////////////////////////////////
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

//                 ///////////////////////////////////////////////
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
//                 fout = new FileOutputStream("verify" +"1.iso19794");
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


//         //Import previous sample data
//         ISOminutiaeBuffer2 = new byte[maxSize[0]];
//         System.out.println("Importing template");

//         try{
//             File samplePath = new File("badam.iso19794");
//             FileInputStream previousSample = new FileInputStream(samplePath);
//             byte[] readPrevSamp = new byte[(int)samplePath.length()];
//             previousSample.read(readPrevSamp);
//             ISOminutiaeBuffer2=readPrevSamp;
//             previousSample.close();
//         }
//         catch(IOException e)
//         {
//             System.out.println("Khai k ho i dont know");
//         }

//         boolean[] matched = new boolean[1];
//         int[] score = new int[1];

//         System.out.println("$$$$$$$$$$$$$ " + ISOminutiaeBuffer2);
//                 ///////////////////////////////////
//         //Match ISO19794 Templates
//         System.out.println("--------");
//         matched[0] = false;
//         score[0] = 0;
//         System.out.println("Call SetTemplateFormat(ISO19794)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");
//         System.out.println("Call MatchIsoTemplates()");
//         err = sgfplib.MatchTemplate(ISOminutiaeBuffer1, ISOminutiaeBuffer2, SGFDxSecurityLevel.SL_NORMAL, matched);
//         // err = sgfplib.MatchIsoTemplate(ISOminutiaeBuffer1, 0, ISOminutiaeBuffer2, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchISOTemplates returned : [" + err + "]");
//         System.out.println("ISO-1 <> ISO-2 Match Result : [" + matched[0] + "]");
//         System.out.println("Call GetIsoMatchingScore()");
//         err = sgfplib.GetMatchingScore(ISOminutiaeBuffer1, ISOminutiaeBuffer2,score);
//         // err = sgfplib.GetIsoMatchingScore(ISOminutiaeBuffer1, 0, ISOminutiaeBuffer2, 0, score);
//         System.out.println("GetIsoMatchingScore returned : [" + err + "]");
//         System.out.println("ISO-1  <> ISO-2 Match Score : [" + score[0] + "]");


//         return new VerifyFinger("finger print match: "+matched[0] + " and match score is: "+score[0]);
//     }

// }
