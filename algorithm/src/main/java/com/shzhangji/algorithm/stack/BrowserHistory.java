package com.shzhangji.algorithm.stack;

import java.util.ArrayDeque;
import java.util.List;

// https://leetcode.com/problems/design-browser-history/
public class BrowserHistory {
  public static void main(String[] args) {
    BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
    browserHistory.visit("google.com");            // You are in "leetcode.com". Visit "google.com"
    browserHistory.visit("facebook.com");          // You are in "google.com". Visit "facebook.com"
    browserHistory.visit("youtube.com");           // You are in "facebook.com". Visit "youtube.com"
    System.out.println(browserHistory.back(1));    // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
    System.out.println(browserHistory.back(1));    // You are in "facebook.com", move back to "google.com" return "google.com"
    System.out.println(browserHistory.forward(1)); // You are in "google.com", move forward to "facebook.com" return "facebook.com"
    browserHistory.visit("linkedin.com");          // You are in "facebook.com". Visit "linkedin.com"
    System.out.println(browserHistory.forward(2)); // You are in "linkedin.com", you cannot move forward any steps.
    System.out.println(browserHistory.back(2));    // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
    System.out.println(browserHistory.back(7));    // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
  }

  ArrayDeque<String> backs;
  ArrayDeque<String> forwards;

  public BrowserHistory(String homepage) {
    backs = new ArrayDeque<>(List.of(homepage));
    forwards = new ArrayDeque<>();
  }

  public void visit(String url) {
    backs.push(url);
    forwards.clear();
  }

  public String back(int steps) {
    while (steps > 0) {
      if (backs.size() == 1) break;
      forwards.push(backs.pop());
      --steps;
    }
    return backs.peek();
  }

  public String forward(int steps) {
    while (steps > 0) {
      if (forwards.isEmpty()) break;
      backs.push(forwards.pop());
      --steps;
    }
    return backs.peek();
  }
}
