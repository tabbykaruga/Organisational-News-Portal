package Dao;

import model.GeneralNews;
import org.sql2o.Connection;

import java.util.List;

public class Sql2oGeneralNews implements GeneralNewsDao {
    private final String DATABASE_TYPE = "General News";

    @Override
    public List<GeneralNews> getAllGeneralNews() {
        String sql = "SELECT * FROM news";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(GeneralNews.class);
        }
    }

    @Override
    public void saveGeneralNews(GeneralNews generalNews) {
        String sql = "INSERT INTO news (newsInfo, type) VALUES (:newsInfo, :type)";
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("newsInfo", generalNews.getNewsInfo())
                    .addParameter("type", DATABASE_TYPE)
                    .executeUpdate()
                    .getKey();
            generalNews.setId(id);
        }
    }

    @Override
    public GeneralNews getGeneralNewsById(int id) {
        return null;
    }

    @Override
    public void updateGeneralNewsById(int id) {

    }
}