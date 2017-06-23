package main.controller;

/**
 * Created by kpant on 6/23/17.
 */
public abstract class AbstractController {
    private ModelAccess modelAccess;


    public AbstractController(ModelAccess modelAccess) {
        this.modelAccess = modelAccess;
    }

    public ModelAccess getModelAccess() {
        return modelAccess;
    }


}
