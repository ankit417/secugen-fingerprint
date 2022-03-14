// /*
//  * JSGFPLibTest.java
//  *
//  * Created on February 4, 2005, 6:52 PM
//  */
// package SecuGen.FDxSDKPro.samples;

// import java.io.*;
// import SecuGen.FDxSDKPro.jni.*;

// /**
//  *
//  * @author  Dan Riley
//  */
// public class JSGFPLibTest {
    
//     /** Creates a new instance of JFPLibTest */
//     public JSGFPLibTest() {
//     }
    
//     /**
//      * @param args the command line arguments
//      */
//     public static void main(String[] args) {
//         // TODO code application logic here
//         long err;
//         byte kbBuffer[] = new byte[100];
//         byte kbWhichFinger[] = new byte[100];
//         int fingerLength = 0;
//         String finger = new String("Finger");
//         byte[] imageBuffer1;
//         byte[] imageBuffer2;
//         byte[] SG400minutiaeBuffer1;
//         byte[] ANSIminutiaeBuffer1;
//         byte[] ISOminutiaeBuffer1;
//         byte[] SG400minutiaeBuffer2;
//         byte[] ANSIminutiaeBuffer2;
//         byte[] ISOminutiaeBuffer2;
//         FileOutputStream fout = null;
//         PrintStream fp = null;

//         //Initialize fingerprint prompt buffer
//         for (int i=0; i < kbWhichFinger.length; ++i)
//            kbWhichFinger[i] = 0x00;

//         System.out.println("");
//         System.out.println("###############################");
//         System.out.println("SecuGen FDx SDK Pro for Java");
//         System.out.println("JSGFPLib JNI Library Test Program");
//         System.out.println("###############################");
//         System.out.println("");
        
        
//         ///////////////////////////////////////////////
//         // Instantiate JSGFPLib object
//         System.out.println("Instantiate JSGFPLib Object");
//         JSGFPLib sgfplib = new JSGFPLib();
//         if ((sgfplib != null) && (sgfplib.jniLoadStatus != SGFDxErrorCode.SGFDX_ERROR_JNI_DLLLOAD_FAILED))
//         {
//             System.out.println(sgfplib);
//         }
//         else
//         {
//             System.out.println("An error occurred while loading JSGFPLIB.DLL JNI Wrapper");
//             return;
//         }

//         ///////////////////////////////////////////////
//         // Init()
//         System.out.println("Call Init(SGFDxDeviceName.SG_DEV_AUTO)");
//         err = sgfplib.Init(SGFDxDeviceName.SG_DEV_AUTO);
//         System.out.println("Init returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // GetLastError()
//         System.out.println("Call GetLastError()");
//         err = sgfplib.GetLastError();
//         System.out.println("Last error returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // GetMinexVersion()
//         int[] extractorVersion = new int[1];
//         int[] matcherVersion = new int[1];
//         System.out.println("Call GetMinexVersion()");
//         err = sgfplib.GetMinexVersion(extractorVersion, matcherVersion);
//         System.out.println("GetMinexVersion returned : [" + err + "]");
//         System.out.println("Extractor version : [" + extractorVersion[0] + "]");
//         System.out.println("Matcher version : [" + matcherVersion[0] + "]");
        
//         ///////////////////////////////////////////////
//         // OpenDevice()
//         System.out.println("Call OpenDevice(SGPPPortAddr.AUTO_DETECT)");
//         err = sgfplib.OpenDevice(SGPPPortAddr.AUTO_DETECT);
//         System.out.println("OpenDevice returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // GetError()
//         System.out.println("Call GetLastError()");
//         err = sgfplib.GetLastError();
//         System.out.println("GetLastError returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // GetDeviceInfo()
//         System.out.println("Call GetDeviceInfo()");
//         SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();
//         err = sgfplib.GetDeviceInfo(deviceInfo);
//         System.out.println( "GetDeviceInfo returned : [" + err + "]");
//         System.out.println("\tdeviceInfo.DeviceSN:    [" + new String(deviceInfo.deviceSN()) + "]");
//         System.out.println("\tdeviceInfo.Brightness:  [" + deviceInfo.brightness + "]");
//         System.out.println("\tdeviceInfo.ComPort:     [" + deviceInfo.comPort + "]");
//         System.out.println("\tdeviceInfo.ComSpeed:    [" + deviceInfo.comSpeed + "]");
//         System.out.println("\tdeviceInfo.Contrast:    [" + deviceInfo.contrast + "]");
//         System.out.println("\tdeviceInfo.DeviceID:    [" + deviceInfo.deviceID + "]");
//         System.out.println("\tdeviceInfo.FWVersion:   [" + deviceInfo.FWVersion + "]");
//         System.out.println("\tdeviceInfo.Gain:        [" + deviceInfo.gain + "]");
//         System.out.println("\tdeviceInfo.ImageDPI:    [" + deviceInfo.imageDPI + "]");
//         System.out.println("\tdeviceInfo.ImageHeight: [" + deviceInfo.imageHeight + "]");
//         System.out.println("\tdeviceInfo.ImageWidth:  [" + deviceInfo.imageWidth + "]");
        
//         ///////////////////////////////////////////////
//         // SetLedOn(true)
//         System.out.print("Press <Enter> to turn fingerprint sensor LEDs on >> ");
//         try
//         {
//             System.in.read(kbBuffer);
//             System.out.println("Call SetLedOn(true)");
//             err = sgfplib.SetLedOn(true);
//             System.out.println("SetLedOn returned : [" + err + "]");
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception reading keyboard : " + e);
//         }

//         ///////////////////////////////////////////////
//         // SetLedOn(false)
//         System.out.println("Fingerprint Sensor LEDS should now be illuminated.");
//         System.out.print("Press <Enter> to turn fingerprint sensor LEDs off >> ");
//         try
//         {
//             System.in.read(kbBuffer);
//             System.out.println("Call SetLedOn(false)");
//             err = sgfplib.SetLedOn(false);
//             System.out.println("SetLedOn returned : [" + err + "]");
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception reading keyboard : " + e);
//         }

//         ///////////////////////////////////////////////
//         ///////////////////////////////////////////////
//         System.out.println("Fingerprint Sensor LEDS should now be off.");
//         System.out.println("The next tests will require mutiple captures of the same finger.");
//         System.out.print("Which finger would you like to test with? (e.g. left thumb) >> ");
//         try
//         {
//             System.in.read(kbWhichFinger);
//             //Remove CR/NL (<ENTER>)
//             for (int i=0; i < kbWhichFinger.length; ++i)
//             {
//                 if ((kbWhichFinger[i] == 0x0A) || (kbWhichFinger[i] == 0x0D)|| (kbWhichFinger[i] == 0x00))
//                 {
//                     fingerLength = i;
//                     break;
//                 }
//             }
//             if (fingerLength > 0)
//                 finger = new String(kbWhichFinger,0,fingerLength);
//             else finger = new String("finger");
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception reading keyboard : " + e);
//         }


//         int[] quality = new int[1];
//         int[] maxSize = new int[1];
//         int[] size = new int[1];
//         SGFingerInfo fingerInfo = new SGFingerInfo();
//         fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
//         fingerInfo.ImageQuality = quality[0];
//         fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
//         fingerInfo.ViewNumber = 1;



// //////////////////////////////////////////////////////////////////////////////
// // Finger 1
//         ///////////////////////////////////////////////
//         // getImage() - 1st Capture
//         System.out.println("Call SetLedOn(true)");
//         err =sgfplib.SetLedOn(true);
//         System.out.println("SetLedOn returned : [" + err + "]");
//         System.out.print("Capture 1. Please place [" + finger + "] on sensor with LEDs on and press <ENTER> ");
//         imageBuffer1 = new byte[deviceInfo.imageHeight*deviceInfo.imageWidth];
//         try
//         {
//             System.in.read(kbBuffer);
//             System.out.println("Call GetImage()");
//             err = sgfplib.GetImage(imageBuffer1);
//             System.out.println("GetImage returned : [" + err + "]");
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 err = sgfplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1, quality);
//                 System.out.println("GetImageQuality returned : [" + err + "]");
//                 System.out.println("Image Quality is : [" + quality[0] + "]");
//                 fout = new FileOutputStream(finger + "1.raw");
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
//                 return; //Cannot continue test if image not captured
//             }
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception reading keyboard : " + e);
//         }

//         ///////////////////////////////////////////////
//         // Set Template format SG400
//         System.out.println("Call SetTemplateFormat(SG400)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // Get Max Template Size for SG400
//         System.out.println("Call GetMaxTemplateSize()");
//         err = sgfplib.GetMaxTemplateSize(maxSize);
//         System.out.println("GetMaxTemplateSize returned : [" + err + "]");
//         System.out.println("Max SG400 Template Size is : [" + maxSize[0] + "]");

//         ///////////////////////////////////////////////
//         // Greate SG400 Template for Finger 1
//         SG400minutiaeBuffer1 = new byte[maxSize[0]];
//         System.out.println("Call CreateTemplate()");
//         err = sgfplib.CreateTemplate(fingerInfo, imageBuffer1, SG400minutiaeBuffer1);
//         System.out.println("CreateTemplate returned : [" + err + "]");
//         err = sgfplib.GetTemplateSize(SG400minutiaeBuffer1, size);
//         System.out.println("GetTemplateSize returned : [" + err + "]");
//         System.out.println("SG400 Template Size is : [" + size[0] + "]");
//         try
//         {
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 fout = new FileOutputStream(finger +"1.sg400");
//                 fp = new PrintStream(fout);
//                 fp.write(SG400minutiaeBuffer1,0, size[0]);
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

//         ///////////////////////////////////////////////
//         // Set Template format ANSI378
//         System.out.println("Call SetTemplateFormat(ANSI378)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // Get Max Template Size for ANSI378
//         System.out.println("Call GetMaxTemplateSize()");
//         err = sgfplib.GetMaxTemplateSize(maxSize);
//         System.out.println("GetMaxTemplateSize returned : [" + err + "]");
//         System.out.println("Max ANSI378 Template Size is : [" + maxSize[0] + "]");

//         ///////////////////////////////////////////////
//         // Greate ANSI378 Template for Finger1
//         ANSIminutiaeBuffer1 = new byte[maxSize[0]];
//         System.out.println("Call CreateTemplate()");
//         err = sgfplib.CreateTemplate(fingerInfo, imageBuffer1, ANSIminutiaeBuffer1);
//         System.out.println("CreateTemplate returned : [" + err + "]");
//         err = sgfplib.GetTemplateSize(ANSIminutiaeBuffer1, size);
//         System.out.println("GetTemplateSize returned : [" + err + "]");
//         System.out.println("ANSI378 Template Size is : [" + size[0] + "]");
//         try
//         {
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 fout = new FileOutputStream(finger +"1.ansi378");
//                 fp = new PrintStream(fout);
//                 fp.write(ANSIminutiaeBuffer1,0, size[0]);
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

//         ///////////////////////////////////////////////
//         // Set Template format ISO19794
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
//                 fout = new FileOutputStream(finger +"1.iso19794");
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



// //////////////////////////////////////////////////////////////////////////////
// // Finger 2
//         ///////////////////////////////////////////////
//         // getImage() - 2nd Capture
//         System.out.println("Call SetLedOn(true)");
//         err =sgfplib.SetLedOn(true);
//         System.out.println("SetLedOn returned : [" + err + "]");
//         System.out.print("Capture 2. Please place [" + finger + "] on sensor with LEDs on and press <ENTER> ");
//         imageBuffer2 = new byte[deviceInfo.imageHeight*deviceInfo.imageWidth];
//         try
//         {
//             System.in.read(kbBuffer);
//             System.out.println("Call GetImage()");
//             err = sgfplib.GetImage(imageBuffer2);
//             System.out.println("GetImage returned : [" + err + "]");
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 err = sgfplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer2, quality);
//                 System.out.println("GetImageQuality returned : [" + err + "]");
//                 System.out.println("Image Quality is : [" + quality[0] + "]");
//                 fout = new FileOutputStream(finger + "2.raw");
//                 fp = new PrintStream(fout);
//                 fp.write(imageBuffer2,0, imageBuffer2.length);
//                 fp.close();
//                 fout.close();
//                 fp = null;
//                 fout = null;
//             }
//             else
//             {
//                 System.out.println("ERROR: Fingerprint image capture failed for sample2.");
//                 return; //Cannot continue test if image not captured
//             }
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception reading keyboard : " + e);
//         }

//         ///////////////////////////////////////////////
//         // Set Template format SG400
//         System.out.println("--------");
//         System.out.println("Call SetTemplateFormat(SG400)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // Get Max Template Size for SG400
//         System.out.println("Call GetMaxTemplateSize()");
//         err = sgfplib.GetMaxTemplateSize(maxSize);
//         System.out.println("GetMaxTemplateSize returned : [" + err + "]");
//         System.out.println("Max SG400 Template Size is : [" + maxSize[0] + "]");

//         ///////////////////////////////////////////////
//         // Greate SG400 Template for Finger 2
//         SG400minutiaeBuffer2 = new byte[maxSize[0]];
//         System.out.println("Call CreateTemplate()");
//         err = sgfplib.CreateTemplate(fingerInfo, imageBuffer2, SG400minutiaeBuffer2);
//         System.out.println("CreateTemplate returned : [" + err + "]");
//         err = sgfplib.GetTemplateSize(SG400minutiaeBuffer2, size);
//         System.out.println("GetTemplateSize returned : [" + err + "]");
//         System.out.println("SG400 Template Size is : [" + size[0] + "]");
//         try
//         {
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 fout = new FileOutputStream(finger +"1.sg400");
//                 fp = new PrintStream(fout);
//                 fp.write(SG400minutiaeBuffer2,0, size[0]);
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

//         ///////////////////////////////////////////////
//         // Set Template format ANSI378
//         System.out.println("--------");
//         System.out.println("Call SetTemplateFormat(ANSI378)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");

//         ///////////////////////////////////////////////
//         // Get Max Template Size for ANSI378
//         System.out.println("Call GetMaxTemplateSize()");
//         err = sgfplib.GetMaxTemplateSize(maxSize);
//         System.out.println("GetMaxTemplateSize returned : [" + err + "]");
//         System.out.println("Max ANSI378 Template Size is : [" + maxSize[0] + "]");

//         ///////////////////////////////////////////////
//         // Greate ANSI378 Template for Finger 2
//         ANSIminutiaeBuffer2 = new byte[maxSize[0]];
//         System.out.println("Call CreateTemplate()");
//         err = sgfplib.CreateTemplate(fingerInfo, imageBuffer2, ANSIminutiaeBuffer2);
//         System.out.println("CreateTemplate returned : [" + err + "]");
//         err = sgfplib.GetTemplateSize(ANSIminutiaeBuffer2, size);
//         System.out.println("GetTemplateSize returned : [" + err + "]");
//         System.out.println("ANSI378 Template Size is : [" + size[0] + "]");
//         try
//         {
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 fout = new FileOutputStream(finger +"2.ansi378");
//                 fp = new PrintStream(fout);
//                 fp.write(ANSIminutiaeBuffer2,0, size[0]);
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
//                 fout = new FileOutputStream(finger +"2.iso19794");
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
//         //Match SG400 Templates
//         System.out.println("--------");
//         matched[0] = false;
//         score[0] = 0;
//         System.out.println("Call SetTemplateFormat(SG400)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");
//         System.out.println("Call MatchTemplate()");
//         err = sgfplib.MatchTemplate(SG400minutiaeBuffer1, SG400minutiaeBuffer2, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchTemplate returned : [" + err + "]");
//         System.out.println("SG400-1 <> SG400-2 Match Result : [" + matched[0] + "]");
//         System.out.println("Call GetMatchingScore()");
//         err = sgfplib.GetMatchingScore(SG400minutiaeBuffer1, SG400minutiaeBuffer2, score);
//         System.out.println("GetMatchingScore returned : [" + err + "]");
//         System.out.println("SG400-1  <> SG400-2 Match Score : [" + score[0] + "]");


//         ///////////////////////////////////
//         //Match ANSI378 Templates
//         System.out.println("--------");
//         matched[0] = false;
//         score[0] = 0;
//         System.out.println("Call SetTemplateFormat(ANSI378)");
//         err = sgfplib.SetTemplateFormat(SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378);
//         System.out.println("SetTemplateFormat returned : [" + err + "]");
//         System.out.println("Call MatchAnsiTemplates()");
//         err = sgfplib.MatchAnsiTemplate(ANSIminutiaeBuffer1, 0, ANSIminutiaeBuffer2, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchANSITemplates returned : [" + err + "]");
//         System.out.println("ANSI-1 <> ANSI-2 Match Result : [" + matched[0] + "]");
//         System.out.println("Call GetAnsiMatchingScore()");
//         err = sgfplib.GetAnsiMatchingScore(ANSIminutiaeBuffer1, 0, ANSIminutiaeBuffer2, 0, score);
//         System.out.println("GetAnsiMatchingScore returned : [" + err + "]");
//         System.out.println("ANSI-1  <> ANSI-2 Match Score : [" + score[0] + "]");

//         ///////////////////////////////////
//         //Merge ANSI378 Templates
//         System.out.println("--------");
//         err = sgfplib.GetTemplateSizeAfterMerge(ANSIminutiaeBuffer1, ANSIminutiaeBuffer2, size);
//         System.out.println("GetTemplateSizeAfterMerge returned : [" + err + "]");
//         System.out.println("ANSI-1 + ANSI-2 Size : [" + size[0] + "]");
//         byte[] mergedAnsiTemplate1 = new byte[size[0]];
//         err = sgfplib.MergeAnsiTemplate(ANSIminutiaeBuffer1, ANSIminutiaeBuffer2, mergedAnsiTemplate1);
//         System.out.println("MergeAnsiTemplate returned : [" + err + "]");
//         err = sgfplib.MatchAnsiTemplate(ANSIminutiaeBuffer1, 0, mergedAnsiTemplate1, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchANSITemplates returned : [" + err + "]");
//         System.out.println("ANSI-1 <> ANSI-MERGED Match Result : [" + matched[0] + "]");

//         ///////////////////////////////////
//         //View ANSI378 Info
//         System.out.println("--------");
//         SGANSITemplateInfo ansiTemplateInfo = new SGANSITemplateInfo();
//         err = sgfplib.GetAnsiTemplateInfo(mergedAnsiTemplate1, ansiTemplateInfo);
//         System.out.println("GetAnsiTemplateInfo returned : [" + err + "]");
//         System.out.println("   TotalSamples=" + ansiTemplateInfo.TotalSamples);
//         for (int i=0; i<ansiTemplateInfo.TotalSamples; ++i){
//         	System.out.println("   Sample[" + i + "].FingerNumber=" + ansiTemplateInfo.SampleInfo[i].FingerNumber);
//         	System.out.println("   Sample[" + i + "].ImageQuality=" + ansiTemplateInfo.SampleInfo[i].ImageQuality);
//         	System.out.println("   Sample[" + i + "].ImpressionType=" + ansiTemplateInfo.SampleInfo[i].ImpressionType);
// 	        System.out.println("   Sample[" + i + "].ViewNumber=" + ansiTemplateInfo.SampleInfo[i].ViewNumber);
//         }
        
        
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


//         ///////////////////////////////////
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
        	

//         ///////////////////////////////////
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
        
        
//         ///////////////////////////////////
//         //Match Different Templates
//         matched[0] = false;
//         score[0] = 0;
//         System.out.println("--------");
//         System.out.println("Match Different template types ...");
//         System.out.println("Call MatchTemplateEx()");
//         err = sgfplib.MatchTemplateEx(ANSIminutiaeBuffer1, SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378, 0, ANSIminutiaeBuffer2, SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchTemplateEx returned : [" + err + "]");
//         System.out.println("ANSI-1 <> ANSI-2 Match Result : [" + matched[0] + "]");
//         err = sgfplib.MatchTemplateEx(ISOminutiaeBuffer1, SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794, 0, ISOminutiaeBuffer2, SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchTemplateEx returned : [" + err + "]");
//         System.out.println("ISO-1 <> ISO-2 Match Result : [" + matched[0] + "]");
//         err = sgfplib.MatchTemplateEx(ANSIminutiaeBuffer1, SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378, 0, ISOminutiaeBuffer2, SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794, 0, SGFDxSecurityLevel.SL_NORMAL, matched);
//         System.out.println("MatchTemplateEx returned : [" + err + "]");
//         System.out.println("ANSI-1 <> ISO-2 Match Result : [" + matched[0] + "]");
//         System.out.println("Call GetMatchingScoreEx()");
//         err = sgfplib.GetMatchingScoreEx(ANSIminutiaeBuffer1, SGFDxTemplateFormat.TEMPLATE_FORMAT_ANSI378, 0, ISOminutiaeBuffer2, SGFDxTemplateFormat.TEMPLATE_FORMAT_ISO19794, 0, score);
//         System.out.println("GetMatchingScoreEx returned : [" + err + "]");
//         System.out.println("ANSI-1  <> ISO-2 Match Score : [" + score[0] + "]");
        
//         ///////////////////////////////////////////////
//         // getImageEx()
//         System.out.println("--------");
//         System.out.println("Call GetImageEx()");
//         System.out.print("Please place [" + finger + "] on sensor and press <ENTER> ");
//         try
//         {
//             System.in.read(kbBuffer);
//             int image_quality = 50; //0 - 100. 50 or above recommended for registration. 40 or above for verification
//             int timeout = 10000; //10 seconds
//             err = sgfplib.GetImageEx(imageBuffer2,timeout,0,image_quality);
//             System.out.println("GetImageEx returned : [" + err + "]");
//             if (err == SGFDxErrorCode.SGFDX_ERROR_NONE)
//             {
//                 err = sgfplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer2, quality);
//                 System.out.println("GetImageQuality returned : [" + err + "]");
//                 System.out.println("Image Quality is : [" + quality[0] + "]");
//                 fout = new FileOutputStream(finger + "_ex.raw");
//                 fp = new PrintStream(fout);
//                 fp.write(imageBuffer2,0, imageBuffer2.length);
//                 fp.close();
//                 fout.close();
//                 fp = null;
//                 fout = null;
//             }
//             else
//             {
//                 if (err == SGFDxErrorCode.SGFDX_ERROR_NOT_USED)
//                     System.out.println("WARNING: GetImageEx() is not supported on this platform.");
//                 else
//                 	System.out.println("ERROR: Fingerprint image capture failed for sample2.");
//             }
//         }
//         catch (IOException e)
//         {
//             System.out.println("Exception reading keyboard : " + e);
//         }

//         ///////////////////////////////////////////////////////////////////////////////////////////////
//         ///////////////////////////////////////////////////////////////////////////////////////////////        
//         //Test WSQ Processing
//         System.out.println("#######################");
//         System.out.println("TEST WSQ COMPRESSION");        
//         System.out.println("###\n###");
//         byte[] wsqfinger1;
//         int wsqLen;
//         try {
//         	File file = new File("wsq2raw_finger.wsq") ;        	
//             FileInputStream fileInputStream = new FileInputStream(file);
//             wsqLen = fileInputStream.available();
//             System.out.println("WSQ file length is: " + wsqLen);
//             wsqfinger1 = new byte[wsqLen];
//             err = fileInputStream.read(wsqfinger1);
//             System.out.println("Read: " + err + "bytes");
//             fileInputStream.close();
//         } catch (IOException ex){
//             System.out.println("Error: Unable to find fingerprint image wsq2raw_finger.wsq.");
//         	return; 
//         }

        
//         int[] fingerImageOutSize = new int[1];
//         err = sgfplib.WSQGetDecodedImageSize(fingerImageOutSize, wsqfinger1, wsqLen); 
//         System.out.println("WSQGetDecodedImageSize() ret:" +  err); 
//         System.out.println("\tRAW Image size is: " + fingerImageOutSize[0]);    

//         byte[] rawfinger1ImageOut = new byte[fingerImageOutSize[0]];
//         int[] decodeWidth = new int[1];
//         int[] decodeHeight = new int[1];
//         int[] decodePixelDepth = new int[1];
//         int[] decodePPI = new int[1];
//         int[] decodeLossyFlag = new int[1];
//         System.out.println("Decode WSQ File");     
//         err = sgfplib.WSQDecode(rawfinger1ImageOut, decodeWidth, decodeHeight, decodePixelDepth, decodePPI, decodeLossyFlag, wsqfinger1, wsqLen);
//         System.out.println("\twidth:\t\t"+ decodeWidth[0]); 
//         System.out.println("\theight:\t\t"+ decodeHeight[0]); 
//         System.out.println("\tdepth:\t\t"+ decodePixelDepth[0]); 
//         System.out.println("\tPPI:\t\t"+ decodePPI[0]);
//         System.out.println("\tLossy Flag\t"+ decodeLossyFlag[0]);
//         if ((decodeWidth[0] == 258) && (decodeHeight[0] == 336))
//             System.out.println("\t+++PASS");
//         else
//             System.out.println("\t+++FAIL!!!!!!!!!!!!!!!!!!");

//         System.out.println("--------");      
//         byte[] rawfinger1;
//         int encodeWidth=258;
//         int encodeHeight=336;
//         int encodePixelDepth=8;
//         int encodePPI=500;
       		
//         int rawLen;
//         try {
//         	File file = new File("raw2wsq_finger.raw") ;
//         	FileInputStream fileInputStream = new FileInputStream(file);
//             rawLen = fileInputStream.available();
//             System.out.println("RAW file length is: " + rawLen);
//             rawfinger1 = new byte[rawLen];
//             err = fileInputStream.read(rawfinger1);
//             System.out.println("Read: " + err + "bytes");
//             fileInputStream.close();
//         } catch (IOException ex){
//             System.out.println("Error: Unable to find fingerprint image raw2wsq_finger.raw.");
//         	return; 
//         }

//         int[] wsqImageOutSize = new int[1];
//         err = sgfplib.WSQGetEncodedImageSize(wsqImageOutSize, SGWSQLib.BITRATE_5_TO_1, rawfinger1, encodeWidth, encodeHeight, encodePixelDepth, encodePPI);
//         System.out.println("WSQGetEncodedImageSize() ret:" +  err); 
//         System.out.println("WSQ Image size is: " + wsqImageOutSize[0]);
//         if (wsqImageOutSize[0] == 20200)
//             System.out.println("\t+++PASS");
//         else
//             System.out.println("\t+++FAIL!!!!!!!!!!!!!!!!!!");

//         byte[] wsqfinger1ImageOut = new byte[wsqImageOutSize[0]];
//         err = sgfplib.WSQEncode(wsqfinger1ImageOut, SGWSQLib.BITRATE_5_TO_1, rawfinger1, encodeWidth, encodeHeight, encodePixelDepth, encodePPI);
//         System.out.println("WSQEncode() ret:" +  err); 
     
//         err = sgfplib.WSQGetDecodedImageSize(fingerImageOutSize, wsqfinger1ImageOut, wsqImageOutSize[0]); 
//         System.out.println("WSQGetDecodedImageSize() ret:" +  err); 
//         System.out.println("RAW Image size is: " + fingerImageOutSize[0]);
 
//         byte[] rawfinger2ImageOut = new byte[fingerImageOutSize[0]];
//         err = sgfplib.WSQDecode(rawfinger2ImageOut, decodeWidth, decodeHeight, decodePixelDepth, decodePPI, decodeLossyFlag, wsqfinger1, wsqLen);
//         System.out.println("WSQDecode() ret:" +  err); 
//         System.out.println("\twidth:\t\t"+ decodeWidth[0]); 
//         System.out.println("\theight:\t\t"+ decodeHeight[0]); 
//         System.out.println("\tdepth:\t\t"+ decodePixelDepth[0]); 
//         System.out.println("\tPPI:\t\t"+ decodePPI[0]);
//         System.out.println("\tLossy Flag\t"+ decodeLossyFlag[0]);
//         if ((decodeWidth[0] == 258) && (decodeHeight[0] == 336))
//             System.out.println("\t+++PASS");
//         else
//             System.out.println("\t+++FAIL!!!!!!!!!!!!!!!!!!");

//         ///////////////////////////////////////////////////////////////////////////////////////////////
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


//         ///////////////////////////////////////////////
//         // Close JSGFPLib native library
//         System.out.println("Call Close()");
//         sgfplib.Close();
//         System.out.println("Close returned : [" + err + "]");

//         sgfplib = null;
//         imageBuffer1 = null;
//         imageBuffer2 = null;
//         SG400minutiaeBuffer1 = null;
//         ANSIminutiaeBuffer1 = null;
//         ISOminutiaeBuffer1 = null;
//         SG400minutiaeBuffer2 = null;
//         ANSIminutiaeBuffer2 = null;
//         ISOminutiaeBuffer2 = null;

//     }    
// }
