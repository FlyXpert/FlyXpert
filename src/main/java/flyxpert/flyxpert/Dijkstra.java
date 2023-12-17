package flyxpert.flyxpert;
import javafx.util.Pair;

import java.util.*;

public class Dijkstra
{
    private static final int size = 101;
    private static int distance[], previous[];
    private static PriorityQueue<Node> priorityQueue;
    List <List <Node>> adj = new ArrayList<List<Node>>(size);

    public Dijkstra()
    {
        previous = new int[size];
        distance = new int[size];
        priorityQueue = new PriorityQueue<Node>(size, new Node());
        for(int i = 0; i < size; i++)
        {
            distance[i] = Integer.MAX_VALUE;
            previous[i] = 0;
            adj.add(new ArrayList<Node>());
        }
    }
    public void initialize(int edges)
    {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < edges; i++)
        {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            adj.get(u).add(new Node(v, w));
            adj.get(v).add(new Node(u, w));
        }
    }
    void run(int source)
    {


        priorityQueue.add(new Node(source, 0));
        distance[source] = 0;

        while(!priorityQueue.isEmpty())
        {
            Node node = priorityQueue.poll();
            if(node.cost > distance[node.node])
                continue;
            for(int i = 0; i < adj.get(node.node).size(); i++)
            {
                Node current = adj.get(node.node).get(i);
                if(distance[current.node] > node.cost + current.cost)
                {
                    previous[current.node] = node.node;
                    distance[current.node] = node.cost + current.cost;
                    priorityQueue.add(new Node(current.node, distance[current.node]));
                }
            }
        }
    }
    void printShortestPathFromSource(int node)
    {
        Stack <Integer> path = new Stack<>();
        while(node != 0)
        {
            path.push(node);
            node = previous[node];
        }
        while(!path.isEmpty())
        {
            System.out.printf("%d ", path.pop());
        }
        System.out.print("\n");
    }
    class Node implements Comparator<Node> {

        public int node;
        public int cost;

        public Node() {}

        public Node(int node, int cost)
        {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2)
        {

            if (node1.cost < node2.cost)
                return -1;

            if (node1.cost > node2.cost)
                return 1;

            return 0;
        }
    }
}
