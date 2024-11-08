package net.dongliu.apk.parser;

import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.ApkSigner;
import net.dongliu.apk.parser.bean.CertificateMeta;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Locale;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApkFileTest {
    
    @Test
    public void testSettings() throws IOException {
        System.out.println( "----------------------------------------------------------------");
        String apkToTest = "apks/Settings.apk";
        if( getClass().getClassLoader().getResource(apkToTest) == null ) {
            System.out.println ("File: " + apkToTest + " does not exist.");
            return;
        }
        String path = getClass().getClassLoader().getResource(apkToTest).getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            apkFile.setPreferredLocale(Locale.ENGLISH);
            ApkMeta apkMeta = apkFile.getApkMeta();
            //System.out.println( "" + apkFile.getManifestXml() );
            
            System.out.println( "Version: " + apkFile.getApkMeta().getVersionName() );
            System.out.println( "PackageName: " + apkFile.getApkMeta().getPackageName() );
            System.out.println( "Name: " + apkFile.getApkMeta().getLabel() );
            System.out.println( "Icon: " + apkFile.getAllIcons() );
            assertEquals("com.android.settings", apkMeta.getPackageName());
            assertEquals("Settings", apkFile.getApkMeta().getLabel());
        }
    }

    @Test
    public void testBrowser() throws IOException {
        System.out.println( "----------------------------------------------------------------");
        String apkToTest = "apks/Browser.apk";
        if( getClass().getClassLoader().getResource(apkToTest) == null ) {
            System.out.println ("File: " + apkToTest + " does not exist.");
            return;
        }
        String path = getClass().getClassLoader().getResource(apkToTest).getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            apkFile.setPreferredLocale(Locale.ENGLISH);
            ApkMeta apkMeta = apkFile.getApkMeta();
            System.out.println( "" + apkFile.getManifestXml() );
            
            System.out.println( "Version: " + apkFile.getApkMeta().getVersionName() );
            System.out.println( "PackageName: " + apkFile.getApkMeta().getPackageName() );
            System.out.println( "Name: " + apkFile.getApkMeta().getLabel() );
            assertEquals("com.android.browser", apkMeta.getPackageName());
            assertEquals("Browser", apkFile.getApkMeta().getLabel());
        }
    }

    @Test
    public void testCamera() throws IOException {
        System.out.println( "----------------------------------------------------------------");
        String apkToTest = "apks/Camera2.apk";
        if( getClass().getClassLoader().getResource(apkToTest) == null ) {
            System.out.println ("File: " + apkToTest + " does not exist.");
            return;
        }
        String path = getClass().getClassLoader().getResource(apkToTest).getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            apkFile.setPreferredLocale(Locale.ENGLISH);
            ApkMeta apkMeta = apkFile.getApkMeta();
            System.out.println( "" + apkFile.getManifestXml() );
            
            System.out.println( "Version: " + apkFile.getApkMeta().getVersionName() );
            System.out.println( "PackageName: " + apkFile.getApkMeta().getPackageName() );
            System.out.println( "Name: " + apkFile.getApkMeta().getLabel() );
            assertEquals("com.android.camera2", apkMeta.getPackageName());
            assertEquals("Camera", apkFile.getApkMeta().getLabel());
        }
    }
    
    @Test
    public void testPlayAutoInstallConfig() throws IOException {
        System.out.println( "----------------------------------------------------------------");
        String apkToTest = "apks/PlayAutoInstallConfig.apk";
        if( getClass().getClassLoader().getResource(apkToTest) == null ) {
            System.out.println ("File: " + apkToTest + " does not exist.");
            return;
        }
        String path = getClass().getClassLoader().getResource(apkToTest).getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            apkFile.setPreferredLocale(Locale.ENGLISH);
            ApkMeta apkMeta = apkFile.getApkMeta();
            System.out.println( "" + apkFile.getManifestXml() );
            
            System.out.println( "Version: " + apkFile.getApkMeta().getVersionName() );
            System.out.println( "PackageName: " + apkFile.getApkMeta().getPackageName() );
            System.out.println( "Name: " + apkFile.getApkMeta().getLabel() );
            assertEquals("android.autoinstalls.config.google.nexus", apkMeta.getPackageName());
            assertEquals("Device configuration", apkFile.getApkMeta().getLabel());
        }
    }

    @Test
    public void testParserMeta() throws IOException {
        String path = getClass().getClassLoader().getResource("apks/Twitter_v7.93.2.apk").getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            apkFile.setPreferredLocale(Locale.ENGLISH);
            ApkMeta apkMeta = apkFile.getApkMeta();
            assertEquals("Twitter", apkMeta.getLabel());
        }
    }

    @Test
    public void testParserMeta_Type_0204() throws IOException {
        String path = getClass().getClassLoader().getResource("apks/NetworkStack_210000000.apk").getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            apkFile.setPreferredLocale(Locale.ENGLISH);
            ApkMeta apkMeta = apkFile.getApkMeta();
            assertEquals("NetworkStack", apkMeta.getLabel());
        }
    }

    @Test
    public void testGetSignature() throws IOException, CertificateException {
        String path = getClass().getClassLoader().getResource("apks/Twitter_v7.93.2.apk").getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            List<ApkSigner> apkSingers = apkFile.getApkSingers();
            assertEquals(1, apkSingers.size());
            ApkSigner apkSigner = apkSingers.get(0);
            assertEquals("META-INF/CERT.RSA", apkSigner.getPath());
            List<CertificateMeta> certificateMetas = apkSigner.getCertificateMetas();
            assertEquals(1, certificateMetas.size());
            CertificateMeta certificateMeta = certificateMetas.get(0);
            assertEquals("69ee076cc84f4d94802d61907b07525f", certificateMeta.getCertMd5());
        }
    }

    @Test
    public void testAppIsNotDebuggable() throws IOException {
        String path = getClass().getClassLoader().getResource("apks/app-release.apk").getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            assertFalse(apkMeta.isDebuggable());
        }
    }

    @Test
    public void testAppIsDebuggable() throws IOException {
        String path = getClass().getClassLoader().getResource("apks/app-debug.apk").getPath();
        try (ApkFile apkFile = new ApkFile(path)) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            assertTrue(apkMeta.isDebuggable());
        }
    }
}
