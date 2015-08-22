package swat_cat.com.imagefetcher.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import swat_cat.com.imagefetcher.models.DaoMaster;
import swat_cat.com.imagefetcher.models.DaoSession;
import swat_cat.com.imagefetcher.models.Image;
import swat_cat.com.imagefetcher.models.ImageDao;

/**
 * Created by Dell on 22.08.2015.
 */
public class DataBaseUtils {
    private Context context;
    private ImageDao imageDao;

    public DataBaseUtils(Context context) {
        this.context = context;
        dbInit();
    }

    void dbInit(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "image-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        imageDao = daoSession.getImageDao();
    }

    public ArrayList<Image> getFavoriteImages(){
        return new ArrayList<>(imageDao.loadAll());
    }

    public long addToFavorites(Image image){
        return imageDao.insert(image);
    }

    public void removeFromFavorites(Image image) {
        imageDao.delete(image);
    }
}
