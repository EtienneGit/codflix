package com.codflix.backend.features.media;

import com.codflix.backend.core.Template;
import com.codflix.backend.features.episode.EpisodeDao;
import com.codflix.backend.models.Episode;
import com.codflix.backend.models.Media;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaController {
    private final MediaDao mediaDao = new MediaDao();
    private final EpisodeDao episodeDao = new EpisodeDao();

    public String list(Request request, Response response) {
        List<Media> medias;

        String title = request.queryParams("title");

        if (title != null && !title.isEmpty()) {
            medias = mediaDao.filterMedias(title);
        } else {
            medias = mediaDao.getAllMedias();
        }

        Map<String, Object> model = new HashMap<>();
        model.put("medias", medias);
        return Template.render("media_list.html", model);
    }

    public String detail(Request request, Response res) {
        int id = Integer.parseInt(request.params(":id"));
        Media media = mediaDao.getMediaById(id);
        List<Episode> episodes = episodeDao.getEpisodesBySerie(id);

        Map<String, Object> model = new HashMap<>();
        model.put("media", media);
        model.put("episodes", episodes);
        System.out.println(model.get("episodes"));
        return Template.render("media_detail.html", model);
    }
}
