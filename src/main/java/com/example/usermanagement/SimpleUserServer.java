package com.example.usermanagement;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleUserServer {
    private static final Map<String, User> users = new ConcurrentHashMap<>();
    private static int userCounter = 1;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        server.createContext("/users", new UserHandler());
        server.setExecutor(null);
        server.start();
        
        System.out.println("Server started on http://localhost:8080");
        System.out.println("Available endpoints:");
        System.out.println("GET /users - Get all users");
        System.out.println("POST /users - Create user (send JSON: {\"name\":\"John\",\"email\":\"john@email.com\",\"age\":25})");
    }

    static class UserHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String response = "";
            int statusCode = 200;

            try {
                if ("GET".equals(method)) {
                    response = getAllUsers();
                } else if ("POST".equals(method)) {
                    String body = readRequestBody(exchange);
                    response = createUser(body);
                } else {
                    response = "Method not allowed";
                    statusCode = 405;
                }
            } catch (Exception e) {
                response = "Error: " + e.getMessage();
                statusCode = 500;
            }

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String readRequestBody(HttpExchange exchange) throws IOException {
            InputStream is = exchange.getRequestBody();
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = is.read()) != -1) {
                sb.append((char) ch);
            }
            return sb.toString();
        }

        private String getAllUsers() {
            StringBuilder json = new StringBuilder("[");
            boolean first = true;
            for (User user : users.values()) {
                if (!first) json.append(",");
                json.append(user.toJson());
                first = false;
            }
            json.append("]");
            return json.toString();
        }

        private String createUser(String body) {
            // Simple JSON parsing
            String name = extractJsonValue(body, "name");
            String email = extractJsonValue(body, "email");
            String ageStr = extractJsonValue(body, "age");
            
            if (name == null || email == null) {
                throw new RuntimeException("Name and email are required");
            }
            
            int age = ageStr != null ? Integer.parseInt(ageStr) : 0;
            String id = String.valueOf(userCounter++);
            
            User user = new User(id, name, email, age);
            users.put(id, user);
            
            return user.toJson();
        }

        private String extractJsonValue(String json, String key) {
            String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]+)\"";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = p.matcher(json);
            if (m.find()) {
                return m.group(1);
            }
            
            // Try without quotes for numbers
            pattern = "\"" + key + "\"\\s*:\\s*([0-9]+)";
            p = java.util.regex.Pattern.compile(pattern);
            m = p.matcher(json);
            if (m.find()) {
                return m.group(1);
            }
            
            return null;
        }
    }

    static class User {
        private String id;
        private String name;
        private String email;
        private int age;

        public User(String id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }

        public String toJson() {
            return String.format("{\"id\":\"%s\",\"name\":\"%s\",\"email\":\"%s\",\"age\":%d}", 
                               id, name, email, age);
        }
    }
}