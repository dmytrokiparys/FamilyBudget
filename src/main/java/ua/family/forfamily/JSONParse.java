package ua.family.forfamily;

import javafx.scene.chart.XYChart;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class JSONParse {
    private int totalGain = 0;
    private int totalCost = 0;

    public int getTotalGain(){
        return totalGain;
    }
    public int getTotalCost(){
        return totalCost;
    }

    public void addMoney(String addedMoney, String amountMoney, String currentData, String who){
        JSONObject resultJson = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONObject jsonData = new JSONObject();
        JSONObject jsonWho = new JSONObject();
        JSONObject jsonAdded = new JSONObject();
        JSONObject jsonCont = new JSONObject();
        try {
            File file = new File("amount.json");

            if(file.exists()){
                Object obj = parser.parse(new FileReader("amount.json"));
                jsonCont = (JSONObject) obj;
                resultJson = jsonCont;
                if(jsonCont.containsKey(currentData)){
                    jsonData = (JSONObject) jsonCont.get(currentData);
                    if(jsonData.containsKey(who)){
                        jsonWho = (JSONObject) jsonData.get(who);
                        if(jsonWho.containsKey("Added")){

                            String added = (String) jsonWho.get("Added");

                            jsonWho.put("Added", String.valueOf(Integer.parseInt(added) + Integer.parseInt(addedMoney)));
                            resultJson.put(currentData, jsonWho);
                            resultJson.put(currentData, jsonData);

                        } else{
                            jsonWho.put("Added", addedMoney);
                            resultJson.put(currentData, jsonWho);
                            resultJson.put(currentData, jsonData);
                        }
                    } else{
                        jsonAdded.put("Added", addedMoney);
                        jsonWho.put("Added", addedMoney);
                        jsonData.put(who, jsonAdded);
                        resultJson.put(currentData, jsonWho);
                        resultJson.put(currentData, jsonData);
                    }
                } else{
                    jsonAdded.put("Added", addedMoney);
                    jsonWho.put(who, jsonAdded);
                    resultJson.put(currentData, jsonWho);
                }
            } else{
                jsonAdded.put("Added", addedMoney);
                jsonWho.put(who, jsonAdded);
                resultJson.put(currentData, jsonWho);
                resultJson.put("Amount", amountMoney);
            }

            resultJson.put("Amount", amountMoney);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        try {
            FileWriter file = new FileWriter("amount.json");
            file.write(resultJson.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void takeMoney(String spentMoney, String amountMoney, String currentData, String who, String purpose) {

       JSONObject resultJson = new JSONObject();
       JSONParser parser = new JSONParser();
       JSONObject jsonData = new JSONObject();
       JSONObject jsonWho = new JSONObject();
       JSONObject jsonCosts = new JSONObject();
       JSONObject jsonCont = new JSONObject();
       try {
           File file = new File("amount.json");

           if(file.exists()) {
               Object obj = parser.parse(new FileReader("amount.json"));
               jsonCont = (JSONObject) obj;
               resultJson = jsonCont;
               if (jsonCont.containsKey(currentData)) {
                   jsonData = (JSONObject) jsonCont.get(currentData);
                   if (jsonData.containsKey(who)) {
                       jsonWho = (JSONObject) jsonData.get(who);
                       if (jsonWho.containsKey("Costs")) {
                           jsonCosts = (JSONObject) jsonWho.get("Costs");
                           if (jsonCosts.containsKey(purpose)) {
                               String cost = (String) jsonCosts.get(purpose);

                               jsonCosts.put(purpose, String.valueOf(Integer.parseInt(cost) + Integer.parseInt(spentMoney)));
                               jsonWho.put("Costs", jsonCosts);
                               resultJson.put(currentData, jsonWho);
                               resultJson.put(currentData, jsonData);
                           } else {
                               jsonCosts.put(purpose, spentMoney);
                               jsonWho.put("Costs", jsonCosts);
                               jsonData.put(who, jsonWho);
                               resultJson.put(currentData, jsonData);
                           }
                       } else {
                           jsonCosts.put(purpose, spentMoney);
                           jsonWho.put("Costs", jsonCosts);
                           jsonData.put(who, jsonWho);
                           resultJson.put(currentData, jsonData);
                       }
                   } else {
                       jsonCosts.put(purpose, spentMoney);
                       jsonWho.put("Costs", jsonCosts);
                       jsonData.put(who, jsonWho);
                       resultJson.put(currentData, jsonData);
                   }
               } else {
                   jsonCosts.put(purpose, spentMoney);
                   jsonWho.put("Costs", jsonCosts);
                   jsonData.put(who, jsonWho);
                   resultJson.put(currentData, jsonData);
               }
           } else{
               jsonCosts.put(purpose, spentMoney);
               jsonWho.put("Costs", jsonCosts);
               jsonData.put(who, jsonWho);
               resultJson.put(currentData, jsonData);
           }

           resultJson.put("Amount", amountMoney);
       } catch (IOException | ParseException e) {
           e.printStackTrace();
       }

       try {
           FileWriter file = new FileWriter("amount.json");
           file.write(resultJson.toString());
           file.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }


   public String AmountRead(){
       JSONParser parser = new JSONParser();
       try {
           File file = new File("amount.json");
           if(file.exists()){
               Object obj = parser.parse(new FileReader(file));
               JSONObject jsonObject = (JSONObject) obj;
               return (String) jsonObject.get("Amount");
           } else return "0";


       } catch (IOException | ParseException e) {
           e.printStackTrace();
       }
       return null;
   }

    public String getGain(){
        JSONParser parser = new JSONParser();
        int gainSum = 0;

        try {
            Object obj = parser.parse(new FileReader("amount.json"));
            JSONObject jsonObject = (JSONObject) obj;

            for(int i = 1; i <= 31; i++){
                if(jsonObject.containsKey(LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth()  + " " + i)){
                    JSONObject jsonWho = (JSONObject) jsonObject.get(LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth()  + " " + i);
                    if(jsonWho.containsKey("Husband")){
                        JSONObject jsonAdd = (JSONObject) jsonWho.get("Husband");
                        if(jsonAdd.containsKey("Added")){
                            gainSum += Integer.parseInt((String) jsonAdd.get("Added"));
                        }
                    }
                    if(jsonWho.containsKey("Wife")) {
                        JSONObject jsonAdd = (JSONObject) jsonWho.get("Wife");
                        if(jsonAdd.containsKey("Added")){
                            gainSum += Integer.parseInt((String) jsonAdd.get("Added"));
                        }
                    }
                }
            }

            return String.valueOf(gainSum);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCosts(){
        JSONParser parser = new JSONParser();
        int costsSum = 0;

        try {
            Object obj = parser.parse(new FileReader("amount.json"));

            JSONObject jsonObject = (JSONObject) obj;

            for(int i = 1; i <= 31; i++){
                if(jsonObject.containsKey(LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth()  + " " + i)){
                    JSONObject jsonWho = (JSONObject) jsonObject.get(LocalDateTime.now().getYear() + " " + LocalDateTime.now().getMonth()  + " " + i);
                    if(jsonWho.containsKey("Husband")){
                        JSONObject jsonCosts = (JSONObject) jsonWho.get("Husband");
                        if(jsonCosts.containsKey("Costs")){
                            JSONObject jsonCurrentCost = (JSONObject) jsonCosts.get("Costs");
                            MainController mc = new MainController();
                            for(var cost : mc.costs){
                                if(jsonCurrentCost.containsKey(cost)){
                                    costsSum += Integer.parseInt((String) jsonCurrentCost.get(cost));
                                }
                            }
                        }
                    }
                    if(jsonWho.containsKey("Wife")) {
                        JSONObject jsonCosts = (JSONObject) jsonWho.get("Wife");
                        if(jsonCosts.containsKey("Costs")){
                            JSONObject jsonCurrentCost = (JSONObject) jsonCosts.get("Costs");
                            MainController mc = new MainController();
                            for(var cost : mc.costs){
                                if(jsonCurrentCost.containsKey(cost)){
                                    costsSum += Integer.parseInt((String) jsonCurrentCost.get(cost));
                                }
                            }
                        }
                    }
                }
            }

            return String.valueOf(costsSum);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean isJSONContains(String month, String year) {
        boolean isPresent = false;
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("amount.json"));
            JSONObject jsonObject = (JSONObject) obj;

            for(int i = 1; i <= 31; i++)
            {
                if(jsonObject.containsKey(year + " " + month + " " + i)) {
                    isPresent = true;
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return isPresent;
    }


    public XYChart.Series<String, Number> dataSeries(String month, String who, String year){
        int totalGainHusband = 0;
        int totalGainWife = 0;
        int totalCostHusband = 0;
        int totalCostWife = 0;

        Map<String, Integer> costsSumH = new HashMap<>();
        Map<String, Integer> costsSumW = new HashMap<>();

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        if(Objects.equals(who, "Husband")){
            dataSeries.setName("Чоловік");
        } else if (Objects.equals(who, "Wife")) {
            dataSeries.setName("Дружина");
        } else {
            dataSeries.setName(who);
        }


        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("amount.json"));

            JSONObject jsonObject = (JSONObject) obj;
            for(int i = 1; i <= 31; i++) {
                if (jsonObject.containsKey(year + " " + month + " " + i)) {
                    JSONObject jsonWho = (JSONObject) jsonObject.get(year + " " + month + " " + i);
                    if (jsonWho.containsKey("Husband")) {
                        JSONObject jsonCosts = (JSONObject) jsonWho.get("Husband");
                        if (jsonCosts.containsKey("Costs")) {
                            JSONObject jsonCurrentCost = (JSONObject) jsonCosts.get("Costs");
                            MainController mc = new MainController();
                            for (var cost : mc.costs) {
                                if (jsonCurrentCost.containsKey(cost)) {
                                    assert false;
                                    if(costsSumH.containsKey(cost)){
                                        int temp = costsSumH.get(cost) + Integer.parseInt((String) jsonCurrentCost.get(cost));
                                        costsSumH.replace(cost, temp);
                                    } else{
                                        costsSumH.put(cost, Integer.parseInt((String) jsonCurrentCost.get(cost)));
                                    }

                                    totalCost += Integer.parseInt((String) jsonCurrentCost.get(cost));
                                    totalCostHusband += Integer.parseInt((String) jsonCurrentCost.get(cost));
                                }
                            }
                        }
                        if (jsonCosts.containsKey("Added")) {
                            totalGain += Integer.parseInt((String)jsonCosts.get("Added"));
                            totalGainHusband += Integer.parseInt((String)jsonCosts.get("Added"));
                        }
                    }

                    if (jsonWho.containsKey("Wife")) {
                        JSONObject jsonCosts = (JSONObject) jsonWho.get("Wife");
                        if (jsonCosts.containsKey("Costs")) {
                            JSONObject jsonCurrentCost = (JSONObject) jsonCosts.get("Costs");
                            MainController mc = new MainController();
                            for (var cost : mc.costs) {
                                if (jsonCurrentCost.containsKey(cost)) {
                                    if (jsonCurrentCost.containsKey(cost)) {
                                        assert false;
                                        if (costsSumW.containsKey(cost)) {
                                            int temp = costsSumW.get(cost) + Integer.parseInt((String) jsonCurrentCost.get(cost));
                                            costsSumW.replace(cost, temp);
                                        } else {
                                            costsSumW.put(cost, Integer.parseInt((String) jsonCurrentCost.get(cost)));
                                        }

                                        totalCost += Integer.parseInt((String) jsonCurrentCost.get(cost));
                                        totalCostWife += Integer.parseInt((String) jsonCurrentCost.get(cost));
                                    }
                                }
                            }
                        }
                        if (jsonCosts.containsKey("Added")) {
                            totalGain += Integer.parseInt((String)jsonCosts.get("Added"));
                            totalGainWife += Integer.parseInt((String)jsonCosts.get("Added"));
                        }
                    }

                }
            }

            if(Objects.equals(who, "Husband")){
                if(costsSumH.size()>0){
                    for(Map.Entry<String, Integer> entry : costsSumH.entrySet()){
                        dataSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                    }
                }
                dataSeries.getData().add(new XYChart.Data<>("Витрачено", totalCostHusband));
                dataSeries.getData().add(new XYChart.Data<>("Зароблено", totalGainHusband));

            } else if (Objects.equals(who, "Wife")){
                if(!costsSumW.isEmpty()){
                    for(Map.Entry<String, Integer> entry : costsSumW.entrySet()){
                        dataSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                    }
                }
                dataSeries.getData().add(new XYChart.Data<>("Витрачено", totalCostWife));
                dataSeries.getData().add(new XYChart.Data<>("Зароблено", totalGainWife));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return dataSeries;
    }
}
