package com.example.demo;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.io.*;

@SpringBootApplication
public class Thumbanails {

    public static void main(String[] args) throws Exception {
        //	SpringApplication.run(DemoApplication.class, args);

        FFmpegFrameGrabber g = new FFmpegFrameGrabber("D:\\Workspace\\Video\\1-English Grammar in Gujarati- Part-1.mp4");
        g.setFormat("mp4");
        g.start();

        for (int i = 0; i < 50; i++) {
            ImageIO.write(g.grab().getBufferedImage(), "png", new File("D:\\Workspace\\Thumbanails\\Thum-" + System.currentTimeMillis() + ".png"));
        }

        g.stop();
    }

    public void getThumb(String videoFilename, String thumbFilename, int width, int height, int hour, int min, float sec)
            throws IOException, InterruptedException {
		String ffmpegApp="";
    	ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y", "-i", videoFilename, "-vframes", "1",
                "-ss", hour + ":" + min + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height, "-an", thumbFilename);
        Process process = processBuilder.start();
        InputStream stderr = process.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) ;
        process.waitFor();
    }


}

