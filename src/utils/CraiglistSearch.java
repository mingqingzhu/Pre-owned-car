package utils;

import bean.ViewBean;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jdom2.Element;

import javax.lang.model.type.ArrayType;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class CraiglistSearch {

    public static String baiceUrl = "https://sfbay.craigslist.org/search/cta?format=rss";

    public ArrayList<ViewBean> search(String searchTitle, int minPrice, int maxPrice, int minAutoYear, int maxAutoYear) {
        ArrayList<ViewBean> list = new ArrayList<ViewBean>();
        String url = baiceUrl;
        if (searchTitle != null) {
            url += "&query=" + searchTitle;
        }
        if (maxPrice != 0 && minPrice != 0 && maxPrice > minPrice) {
            url += "&min_price=" + minPrice + "&max_price=" + maxPrice;
        }
        if (maxAutoYear != 0 && minAutoYear != 0 && maxAutoYear > minAutoYear) {
            url += "&min_auto_year=" + minPrice + "&max_auto_year=" + maxPrice;
        }
        try {
            URL urlSearch = new URL(url);    //load RSS source
            XmlReader reader = new XmlReader(urlSearch);    //read source
            SyndFeedInput input = new SyndFeedInput();    //get information from the RSS source
            SyndFeed feed = input.build(reader);
            List entries = feed.getEntries();    //get information of sublist of RSS information
            for (int i = 0; i < entries.size(); i++) {
                ViewBean bean = new ViewBean();
                SyndEntry entry = (SyndEntry) entries.get(i);
                String title = entry.getTitle();
                bean.setTitle(title.substring(0, title.indexOf("&#x0024;")));
                bean.setPrice(title.substring(title.indexOf("&#x0024;") + 8, title.length()));
                bean.setLink(entry.getLink());
                bean.setData(entry.getPublishedDate());
                list.add(bean);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
