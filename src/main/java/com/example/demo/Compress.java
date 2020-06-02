package com.example.demo;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

//https://github.com/bramp/ffmpeg-cli-wrapper
//https://www.ffmpeg.org/download.html
public class Compress {

    public static void main(String[] args) throws IOException {

        FFmpeg ffmpeg = new FFmpeg("D:\\Software\\ffmpeg-20200601-dd76226-win64-static\\ffmpeg-20200601-dd76226-win64-static\\bin\\ffmpeg");
        FFprobe ffprobe = new FFprobe("D:\\Software\\ffmpeg-20200601-dd76226-win64-static\\ffmpeg-20200601-dd76226-win64-static\\bin\\ffprobe");

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput("D:\\Workspace\\Video\\1-English Grammar in Gujarati- Part-1.mp4")     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput("D:\\Workspace\\Video\\output.mp4")   // Filename for the destination
                .setFormat("mp4")       // Format is inferred from filename, or can be set
                //  .setTargetSize(250_000) // Aim for a 250KB file
                .disableSubtitle()       // No subtiles
                .setAudioChannels(1)                   // Mono audio
                //  .setAudioChannels(2)
                .setAudioCodec("aac")       // using the aac codec
                .setAudioSampleRate(48_000) // at 48KHz
                .setAudioBitRate(32768)     // at 32 kbit/s
                //  .setAudioBitRate(126000)
                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setVideoResolution(1280, 720) // at 640x480 resolution
                //       .setVideoResolution(1024, 768)
                //       .setVideoResolution(640, 480)
                .setVideoBitRate(762800)
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

// Run a one-pass encode
        executor.createJob(builder).run();

// Or run a two-pass encode (which is better quality at the cost of being slower)
        executor.createTwoPassJob(builder).run();
        System.out.println("completed successfully ");
    }

}
