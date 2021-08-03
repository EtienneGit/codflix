package com.codflix.backend.features.episode;

import com.codflix.backend.core.Database;
import com.codflix.backend.models.Episode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EpisodeDao {
    public List<Episode> getAllEpisodes() {
        List<Episode> episodes = new ArrayList<>();
        Connection connection = Database.get().getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM episode");
            while (rs.next()) {
                Episode episode = new Episode(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );

                episodes.add(episode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return episodes;
    }
}
