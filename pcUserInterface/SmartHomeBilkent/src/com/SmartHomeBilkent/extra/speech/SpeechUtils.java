package com.SmartHomeBilkent.extra.speech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeechUtils {
    //properties
    private Voice voice;
    private VoiceManager voiceManager;

    //constructor
    public SpeechUtils() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = voiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();// Allocating Voice
            try {
                voice.setRate(180);// Setting the rate of the voice
                voice.setPitch(150);// Setting the Pitch of the voice
                voice.setVolume(3);// Setting the volume of the voice

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }
    //methods
    public void SpeakText(String words,Boolean chech) {
        if(chech)
        voice.speak(words);
    }
}
