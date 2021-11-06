package com.lisbon.Lisbon.controller;


import com.lisbon.Lisbon.graph.BFSShortestPath;
import com.lisbon.Lisbon.graph.Dijkstra;
import com.lisbon.Lisbon.graph.EdgeWeightedGraph;
import com.lisbon.Lisbon.model.Stop;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class HomeController {

    String filepath = "src/GTFS-Data/stops.txt";

    @GetMapping("/")
    public String homePage(){


        return "index";
    }

    @GetMapping("/dij")
    public String dij(){


        return "dijkstra";
    }


    @RequestMapping(value = "/getStops")
    public @ResponseBody HashMap getStops() throws IOException, JSONException {

        String filePath = "src/main/java/com/lisbon/Lisbon/Output-Data/stops.txt";
        File file = new File(filePath);
        JSONArray ja = new JSONArray();

        HashMap<Integer, Stop> stopst = new HashMap<>();



            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                if (splitLine.length == 5) {
                    int id = Integer.parseInt(splitLine[0]);
                    String stop_id = splitLine[1];
                    String stop_name = splitLine[2];
                    Stop stop = new Stop();
                    stop.setId(id);
                    stop.setStop_id(stop_id);
                    stop.setStop_name(stop_name);

                    stopst.put(id, stop);

                }

            }

        System.out.println(stopst);

        return stopst;

    }

    //runBFS
    @RequestMapping(value = "/runBFS")
    @ResponseBody
    public ArrayList<Integer> runBFS(@RequestParam("first") Integer first, @RequestParam("second") Integer second ) throws IOException {
        EdgeWeightedGraph graph = new EdgeWeightedGraph("src/main/java/com/lisbon/Lisbon/Output-Data/unweightededges.txt");
        BFSShortestPath bfsSP = new BFSShortestPath();
        bfsSP.bfs(graph, first);
        ArrayList<Integer> suggestedPath = bfsSP.pathTo(second);
        System.out.println("Shortest path from " + first  +" to " + second + " is: ");

        System.out.println(suggestedPath);

        return suggestedPath;
    }

    //runBFSTraversal
    @RequestMapping(value = "/runbfstrasversal")
    @ResponseBody
    public ArrayList<Integer> runbfstrasversal(@RequestParam("first") Integer first) throws IOException {
        EdgeWeightedGraph graph = new EdgeWeightedGraph("src/main/java/com/lisbon/Lisbon/Output-Data/unweightededges.txt");
        BFSShortestPath bfsSP = new BFSShortestPath();
        ArrayList<Integer> bfsTraversal = bfsSP.bfs(graph, first);

        System.out.println(bfsTraversal);

        return bfsTraversal;
    }

    //runDijto
    @RequestMapping(value = "/runDij")
    @ResponseBody
    public ArrayList<Integer> runDij(@RequestParam("first") Integer first, @RequestParam("second") Integer second ) throws IOException {
        EdgeWeightedGraph graph = new EdgeWeightedGraph("src/main/java/com/lisbon/Lisbon/Output-Data/edges.txt");
        Dijkstra dijkstraSP = new Dijkstra();



        dijkstraSP.parse(graph, first);
        ArrayList<Integer> suggestedPath = dijkstraSP.pathTo(second);
        System.out.println("Shortest path from " + first  +" to " + second + " is: ");
        System.out.println(suggestedPath);

        return suggestedPath;
    }


    //runDijsktraTraversal
    @RequestMapping(value = "/rundijkstraversal")
    @ResponseBody
    public ArrayList<Integer> rundijkstraversal(@RequestParam("first") Integer first) throws IOException {
        EdgeWeightedGraph graph = new EdgeWeightedGraph("src/main/java/com/lisbon/Lisbon/Output-Data/edges.txt");
        Dijkstra dijkstraSP = new Dijkstra();
        ArrayList<Integer> dijkstraTraversal = dijkstraSP.parse(graph, first);

        System.out.println(dijkstraTraversal);

        return dijkstraTraversal;
    }
}
