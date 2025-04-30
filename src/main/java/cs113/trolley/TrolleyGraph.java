package cs113.trolley;

import javafx.scene.paint.Color;

import java.util.*;

// ********** Graph Construction ********** //
class TrolleyGraph {
    private List<TrolleyStation> stations;
    private List<TrolleyRoute> routes;

    public TrolleyGraph() {
        stations = new ArrayList<>();
        routes = new ArrayList<>();
    }


    // Add a new station (node) to the graph
    public void addStation(String name, int x, int y) 
    {
        // todo: Implement this method to add a new station
        // Make sure to check if a station with the same name already exists

        if (!getStationByName)
        {
            TrolleyStation station = new TrolleyStation(name, x, y);
        }

        
        if(!stations.contains(station))
        {
            stations.add(station);
        }
    } //end of addStation method


    // Get a station by its name
    public TrolleyStation getStationByName(String name) 
    {
        // todo: Implement this method to find a station by name
        return null;
        Iterator<TrolleyStation> iterator = stations.iterator();
        while (iterator.hasNext())
        {
            TrolleyStation station = iterator.next();
            if (station.getName().equals(name))
            {
                return station;
            }
        }

        for (TrolleyStation station : stations)
        {
            if (station.getName().equals(name))
            {

            }
        }
    }

    // Get all station names
    public Set<String> getStationNames() {
        Set<String> names = new HashSet<>();
        for (TrolleyStation station : stations) {
            names.add(station.getName());
        }
        return names;
    }

    // Add a new route (edge) between two stations
    public void addRoute(String fromStation, String toStation, int weight, Color color) 
    {
        // todo: Implement this method to add a new route
        // Make sure both stations exist before adding the route
        if (getStationByName(fromStation) == null)
        { return; }
        if (getStationByName(toStation) == null)
        { return; }

        TrolleyRoute route = new TrolleyRoute(fromStation, toStation, weight, color);
        routes.add(route);
        route = new TrolleyRoute(toStation, fromStation, weight, color);
        routes.add(route);
    }

    // Get all stations
    public List<TrolleyStation> getStations() {
        return stations;
    }

    // Get all routes
    public List<TrolleyRoute> getRoutes() {
        return routes;
    }

    // ********** Adjacency Lists ********** //
    public List<String> getAdjacentStations(String stationName) 
    {   // todo: Implement this method to find all stations connected to the given station
        List<String> adjacent = new ArrayList<>();
        for (TrolleyRoute route : routes)
        {
            if (route.getFromStation().equals(stationName))
                adjacent.add(route.getToStation());
        }
        return adjacent;
    }

    // Get the weight of a route between two stations
    public int getRouteWeight(String fromStation, String toStation) 
    {
        // todo: Calculate the route weight between stations
        for (TrolleyRoute route : routes)
        {
            if (route.getFromStation().equals(fromStation) && route.getToStation().equals(toStation))
            {
                return route.getWeight();
            }
        }
        return -1; // No direct route
    }

    // ********** Breadth First Search (BFS) ********** //
    public List<String> breadthFirstSearch(String startStation, String endStation) {
        // todo: Implement a BFS (see readme)
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();

        queue.add(startStation);

        while(!queue.isEmpty())
        {
            String current = queue.poll();

            if (current.equals(endStation))
            {
                reconstructPath(parentMap, startStation, endStation);
            }

            visited.add(current);

            List<ArrayList> neighbors = getAdjacentStations(current);
            for (String neighbor : neighbors)
            {
                if(!visited.contains(neighbor))
                {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return null; // No path found
    } //end of breadthFirstSearch method

    // ********** Depth First Search (DFS) ********** //
    public List<String> depthFirstSearch(String startStation, String endStation) {
        // todo: Implement a DFS (see readme)
        return null; // No path found
    }

    // ********** Dijkstra's Algorithm ********** //
    public List<String> dijkstra(String startStation, String endStation) {
        // todo: Implement Dijkstra's Algorithm
        return null; // No path found
    }

    // Helper method to reconstruct the path from start to end using the parent map
    private List<String> reconstructPath(Map<String, String> parentMap, String start, String end) 
    {
        List<String> path = new ArrayList<>();
        String current = end;

        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);

            if (current != null && current.equals(start)) {
                path.add(0, start);
                break;
            }
        }

        return path.size() > 1 ? path : null;
    }
}