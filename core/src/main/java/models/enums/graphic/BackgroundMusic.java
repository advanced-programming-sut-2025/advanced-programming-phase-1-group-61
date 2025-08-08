package models.enums.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public enum BackgroundMusic {
    KHARMALE("SFX/backgroundMusics/Kharmale.mp3","KHARMALE"),
    EMBER("SFX/backgroundMusics/Ember.mp3","EMBER"),
    PARADOX("SFX/backgroundMusics/Paradox.mp3","PARADOX"),
    WITHOUT_LOVE("SFX/backgroundMusics/Without Love.mp3","WITHOUT LOVE");
    private final String internalPath;
//    private final Music music;
    private final String displayName;
    BackgroundMusic(String internalPath,String displayName) {
        this.internalPath = internalPath;
        this.displayName = displayName;
//        music = Gdx.audio.newMusic(Gdx.files.internal(internalPath));
//        music.setLooping(true);
        //music.setVolume(App.getMusicVolume());
    }
    public String getInternalPath() {
        return internalPath;
    }

    public void setVolume(float volume) {
        //App.setMusicVolume(volume);
//        music.setVolume(volume);
    }
    public String getDisplayName() {
        return displayName;
    }
    public static String[] getItems(){
        String[] items = new String[BackgroundMusic.values().length];
        for(int i=0;i<BackgroundMusic.values().length;i++){
            items[i] = BackgroundMusic.values()[i].getDisplayName();
        }
        return items;
    }
    public static BackgroundMusic displayNameToEnum(String displayName){
        BackgroundMusic[] values = BackgroundMusic.values();
        for (BackgroundMusic value : values) {
            if (value.getDisplayName().equals(displayName)) return value;
        }
        return null;
    }
}
