package com.project;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "TestServlet", value = "/test")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}