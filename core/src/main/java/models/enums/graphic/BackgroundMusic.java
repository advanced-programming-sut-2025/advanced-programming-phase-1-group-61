package models.enums.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public enum BackgroundMusic {
    TEST("","");
    private final String internalPath;
    private final Music music;
    private final String displayName;
    BackgroundMusic(String internalPath,String displayName) {
        this.internalPath = internalPath;
        this.displayName = displayName;
        music = Gdx.audio.newMusic(Gdx.files.internal(internalPath));
        music.setLooping(true);
        //music.setVolume(App.getMusicVolume());
    }
    public String getInternalPath() {
        return internalPath;
    }
    public Music getMusic() {
        return music;
    }
    public void setVolume(float volume) {
        //App.setMusicVolume(volume);
        music.setVolume(volume);
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
