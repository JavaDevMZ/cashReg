package com.cashReg.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Internationalizable {

    boolean changeLanguageIfNeeded(HttpServletRequest request);
}
