package bestteam.bestrecipeapp;

import java.io.Serializable;

public class RecipeStep implements Serializable {
    public String title;
    public String content;

    public RecipeStep(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


