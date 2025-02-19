package testServerSocket;

import java.util.Date;

public class SmartReplyEngine {
    public String reply(String message) {
//        switch (message.toLowerCase().trim()){
//            case "hi":
//            case "hello":
//            case "welcome":
//                return "Welcome.";
//            case "how are you ?" :
//            case "how r u ?":
//            case "how are you?" :
//            case "how r u?":
//                return "I'm fine. And you ?";
//            case "time":
//                return new Date().toString();
//            case "bye":
//            case "good bye":
//            case "exit":
//                return "bye";
//            default:
//                return "Sorry. I can't understand ";
//        }
        String trimmedMessage = message.toLowerCase().trim();

        // Greeting responses
        if (trimmedMessage.matches("^(hi|hello|welcome)$")) {
            return "Welcome ya mostafa.";
        }

        // Responses to "How are you?"
        if (trimmedMessage.matches("^(how\\s*are\\s*you\\s*\\?|how\\s*r\\s*u\\s*\\?)$")) {
            return "I'm fine. And you ?";
        }


        // Farewell responses
        if (trimmedMessage.matches("^(bye|good\\s*bye|exit)$")) {
            return "bye";
        }

        // Responses for specific phrases
        if (trimmedMessage.matches("^(bt7bny)$")) {
            return "tb3n";
        }

        // Responses for common questions
        if (trimmedMessage.matches("^(what\\s*is\\s*your\\s*name\\s*\\?|who\\s*are\\s*you\\s*\\?)$")) {
            return "I'm your friendly bot, here to assist you!";
        }

        if (trimmedMessage.matches("^(where\\s*are\\s*you\\s*from\\s*\\?)$")) {
            return "Egypt";
        }

        if (trimmedMessage.matches("^(what\\s*is\\s*your\\s*favorite\\s*color\\s*\\?)$")) {
            return "Black";
        }

        if (trimmedMessage.matches("^(what\\s*can\\s*you\\s*do\\s*\\?)$")) {
            return "I can chat with you, give responses, and much more!";
        }

        if (trimmedMessage.matches("^time$")) {
            return new Date().toString();
        }

        if (trimmedMessage.matches("^\\d+\\s*\\+\\s*\\d+$")) {
            String[] parts = trimmedMessage.split("\\+");
            int result = Integer.parseInt(parts[0].trim()) + Integer.parseInt(parts[1].trim());
            return "The result is: " + result;
        }

        if (trimmedMessage.matches("^(tell\\s*me\\s*a\\s*joke\\s*\\?)$")) {
            return "Why don't skeletons fight each other? They don't have the guts!";
        }

        return "Sorry. I can't understand";
    }
}
