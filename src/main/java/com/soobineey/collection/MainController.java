package com.soobineey.collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {
  // 데이터를 저장하고 보여주기 해쉬맵
  private HashMap<Integer, String> asksPriceHash = new HashMap<>();
  private HashMap<Integer, String> asksQuantityHash = new HashMap<>();
  private HashMap<Integer, String> bidsPriceHash = new HashMap<>();
  private HashMap<Integer, String> bidsQuantityHash = new HashMap<>();

  // 인덱스 페이지 (로그인 페이지)
  @GetMapping("/")
  public String index() {
    return "index";
  }

  // sendController에서 호출하면 코드 실행
  @PostMapping("receiveData")
  @ResponseBody
  public ArrayList<HashMap<Integer, String>> receiveData(@RequestParam("result") String result) {
    System.out.println(result);
    // 받아온 데이터를 JSon 변환을 위한 jsonParser
    JSONParser jsonParser = new JSONParser();
    // 데이터를 저장하고 보여주기 해쉬맵
//    HashMap<Integer, String> asksPriceHash = new HashMap<>();
//    HashMap<Integer, String> asksQuantityHash = new HashMap<>();
//    HashMap<Integer, String> bidsPriceHash = new HashMap<>();
//    HashMap<Integer, String> bidsQuantityHash = new HashMap<>();

    ArrayList<HashMap<Integer, String>> returnData = new ArrayList<>();

    try {
      JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

      // 데이터 각각 매수와 매도로 나눠 jsonArray에 담는다.
      JSONArray asksJsonArray = (JSONArray) jsonObject.get("asks");
      JSONArray bidsJsonArray = (JSONArray) jsonObject.get("bids");

      System.out.println("====== 매도 데이터 ======");
      // jsonArray에 담긴 매도 데이터를 가격과 수량으로 나눠 arrayList에 담는다.
      for (int asksIndex = 0; asksIndex < asksJsonArray.size(); asksIndex++) {
        JSONObject asksPObject = (JSONObject) asksJsonArray.get(asksIndex);
        // 각각의 데이터를 String변수에 담는다.
        String asksPriceData = (String) asksPObject.get("price");
        String asksQuantityData = (String) asksPObject.get("quantity");
        // String변수에 담긴 데이터를 key값과 함께 hashMap에 담는다.
        asksPriceHash.put(asksIndex, asksPriceData);
        asksQuantityHash.put(asksIndex, asksQuantityData);
      }

      System.out.println("====== 매수 데이터 ======");
      // jsonArray에 담긴 매수 데이터를 가격과 수량으로 나눠 arrayList에 담는다.
      for (int bidsIndex = 0; bidsIndex < bidsJsonArray.size(); bidsIndex++) {
        JSONObject bidsObject = (JSONObject) bidsJsonArray.get(bidsIndex);
        // 각각의 데이터를 String변수에 담는다.
        String bidsPriceData = (String) bidsObject.get("price");
        String bidsQuantityData = (String) bidsObject.get("quantity");
        // String변수에 담긴 데이터를 key값과 함께 hashMap에 담는다.
        bidsPriceHash.put(bidsIndex, bidsPriceData);
        bidsQuantityHash.put(bidsIndex, bidsQuantityData);
      }

      returnData.add(asksPriceHash);
      returnData.add(asksQuantityHash);
      returnData.add(bidsPriceHash);
      returnData.add(bidsQuantityHash);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return returnData;
  }

  @PostMapping("searchData")
  public ArrayList<HashMap<Integer, String>> searchData(@RequestParam("sendSearchData") String sendSearchData, @RequestParam("target") String target) {

    System.out.println("sendSearchData");
    System.out.println(sendSearchData);

    // 받아온 데이터를 JSon 변환을 위한 jsonParser
    JSONParser jsonParser = new JSONParser();


    ArrayList<HashMap<Integer, String>> returnData = new ArrayList<>();

    try {
      JSONObject jsonObject = (JSONObject) jsonParser.parse(sendSearchData);

      // 데이터 각각 매수와 매도로 나눠 jsonArray에 담는다.
      JSONArray asksJsonArray = (JSONArray) jsonObject.get("asks");
      JSONArray bidsJsonArray = (JSONArray) jsonObject.get("bids");

      System.out.println("====== 매도 데이터 ======");
      // jsonArray에 담긴 매도 데이터를 가격과 수량으로 나눠 arrayList에 담는다.
      for (int asksIndex = 0; asksIndex < asksJsonArray.size(); asksIndex++) {
        JSONObject asksPObject = (JSONObject) asksJsonArray.get(asksIndex);
        // 각각의 데이터를 String변수에 담는다.
        String asksPriceData = (String) asksPObject.get("price");
        String asksQuantityData = (String) asksPObject.get("quantity");
        // String변수에 담긴 데이터를 key값과 함께 hashMap에 담는다.
        asksPriceHash.put(asksIndex, asksPriceData);
        asksQuantityHash.put(asksIndex, asksQuantityData);
      }

      System.out.println("====== 매수 데이터 ======");
      // jsonArray에 담긴 매수 데이터를 가격과 수량으로 나눠 arrayList에 담는다.
      for (int bidsIndex = 0; bidsIndex < bidsJsonArray.size(); bidsIndex++) {
        JSONObject bidsObject = (JSONObject) bidsJsonArray.get(bidsIndex);
        // 각각의 데이터를 String변수에 담는다.
        String bidsPriceData = (String) bidsObject.get("price");
        String bidsQuantityData = (String) bidsObject.get("quantity");
        // String변수에 담긴 데이터를 key값과 함께 hashMap에 담는다.
        bidsPriceHash.put(bidsIndex, bidsPriceData);
        bidsQuantityHash.put(bidsIndex, bidsQuantityData);
      }

      ArrayList<SortPart> sortArrayList = new ArrayList();

      for (int i = 0; i < 30; ++i) {
        String asksPrice = asksPriceHash.get(i);
        String asksQuantity = asksQuantityHash.get(i);
        String bidsPrice = bidsPriceHash.get(i);
        String bidsQuantity = bidsQuantityHash.get(i);

        sortArrayList.add(new SortPart(target, asksPrice, asksQuantity, bidsPrice, bidsQuantity));
      }
      Collections.sort(sortArrayList);

      asksPriceHash.clear();
      asksQuantityHash.clear();
      bidsPriceHash.clear();
      bidsQuantityHash.clear();

      int key = 0;
      for (SortPart sortPart : sortArrayList) {
        asksPriceHash.put(key, sortPart.getAsksPrice());
        asksQuantityHash.put(key, sortPart.getAsksQuantity());
        bidsPriceHash.put(key, sortPart.getBidsPrice());
        bidsQuantityHash.put(key, sortPart.getBidsQuantity());
        key++;
      }

      returnData.add(asksPriceHash);
      returnData.add(asksQuantityHash);
      returnData.add(bidsPriceHash);
      returnData.add(bidsQuantityHash);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return returnData;
  }
}
