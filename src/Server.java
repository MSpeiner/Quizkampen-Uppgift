public class Server {
}

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))
        ) {
            System.out.println("Client connected");
            String fromClient;

            while ((fromClient = in.readLine()) != null) {
                System.out.println("From Client: " + fromClient);
                out.println("Got: " + fromClient + " from client");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}