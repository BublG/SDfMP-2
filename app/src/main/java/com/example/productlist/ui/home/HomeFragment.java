package com.example.productlist.ui.home;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.productlist.MainActivity;
import com.example.productlist.R;
import com.example.productlist.db.Fish;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private View homeFragmentView;
    private ListView fishListView;
    private RequestQueue mQueue;
    private MyListAdapter myListAdapter;
    private RadioButton wildButton;
    private RadioButton farmedButton;

    private static boolean needToLoad = true;

    private static final String RADIO_BUTTON_TEXT_TEMPLATE = "%s (%d)";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        wildButton = homeFragmentView.findViewById(R.id.wild);
        fishListView = homeFragmentView.findViewById(R.id.fishListView);
        wildButton.setOnClickListener(this::displayWild);
        farmedButton = homeFragmentView.findViewById(R.id.farmed);
        farmedButton.setOnClickListener(this::displayFarmed);

        if (needToLoad) {
            MainActivity.fishDao.deleteAll();
            mQueue = Volley.newRequestQueue(this.getContext());
            loadFish();
            needToLoad = false;
        } else {
            setDataFromDB();
        }
        return homeFragmentView;
    }

    private Fish[] getFishArrayByHarvestType(String harvestType) {
        List<Fish> list = MainActivity.fishDao.findAllByHarvestType(harvestType);
        Fish[] array = new Fish[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private void loadFish() {
        String url = "https://www.fishwatch.gov/api/species";
        JsonArrayRequest request = new JsonArrayRequest(url, response -> {
            for (int i = 0; i < response.length() && i < 50; i++) {
                try {
                    JSONObject fishJson = (JSONObject) response.get(i);
                    Fish fish = convertJSONToFish(fishJson);
                    MainActivity.fishDao.insert(fish);
                } catch (JSONException ignored) {
                }
            }
            setDataFromDB();
        }, error -> {

        });
        mQueue.add(request);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displayFarmed(View view) {
        Fish[] fish = getFishArrayByHarvestType("Farmed");
        String[] mainTitles = Arrays.stream(fish).map(Fish::getSpeciesName).toArray(String[]::new);
        myListAdapter = new MyListAdapter(getActivity(), fish, mainTitles);
        fishListView.setAdapter(myListAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displayWild(View view) {
        Fish[] fish = getFishArrayByHarvestType("Wild");
        System.out.println(fish.length);
        String[] mainTitles = Arrays.stream(fish).map(Fish::getSpeciesName).toArray(String[]::new);
        myListAdapter = new MyListAdapter(getActivity(), fish, mainTitles);
        fishListView.setAdapter(myListAdapter);
    }

    private Fish convertJSONToFish(JSONObject fishJson) throws JSONException {
        Fish fish = new Fish();
        fish.setSpeciesName(fishJson.getString("Species Name"));
        fish.setScientificName(fishJson.getString("Scientific Name"));
        fish.setHarvestType(fishJson.getString("Harvest Type"));
        fish.setCalories(fishJson.getString("Calories"));
        Object imageGallery = fishJson.get("Image Gallery");
        if (!imageGallery.toString().equals("null")) {
            JSONArray images = fishJson.getJSONArray("Image Gallery");
            if (images.length() != 0) {
                fish.setImageSrc(((JSONObject) images.get(0)).getString("src"));
            } else {
                fish.setImageSrc(null);
            }
        }
        return fish;
    }

    @SuppressLint("DefaultLocale")
    private void setDataFromDB() {
        wildButton.setText(String.format(RADIO_BUTTON_TEXT_TEMPLATE, "Wild",
                MainActivity.fishDao.getHarvestTypeQuantity("Wild")));
        farmedButton.setText(String.format(RADIO_BUTTON_TEXT_TEMPLATE, "Farmed",
                MainActivity.fishDao.getHarvestTypeQuantity("Farmed")));
        wildButton.callOnClick();
    }
}