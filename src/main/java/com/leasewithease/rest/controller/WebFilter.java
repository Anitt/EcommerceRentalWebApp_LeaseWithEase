package com.leasewithease.rest.controller;

import java.io.IOException;

import com.leasewithease.rest.AppContext;
import com.leasewithease.rest.session.SessionHandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebFilter {

	@GetMapping("/")
	public String root(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "index.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "index.html";
			} else if (sessionHandler.isLessorLoggedIn()) {
				ret = "renterHomepage.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/index.html")
	public String index(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "index.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "index.html";
			} else if (sessionHandler.isLessorLoggedIn()) {
				ret = "renterHomepage.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/login.html")
	public String login(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "login.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "index.html";
			} else if (sessionHandler.isLessorLoggedIn()) {
				ret = "renterHomepage.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/addProducts.html")
	public String addProducts(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "addProducts.html";
		String ret = page;

		try {
			if (sessionHandler.isLessorLoggedIn()) {
				ret = "addProducts.html";
			} else if(!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/removeProducts.html")
	public String removeProducts(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "removeProducts.html";
		String ret = page;

		try {
			if (sessionHandler.isLessorLoggedIn()) {
				ret = "removeProducts.html";
			}  else if(!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/renteeProductDetails.html")
	public String showRenteeProductDetails(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "renteeProductDetails.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "renteeProductDetails.html";
			} else {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/renterProductDetails.html")
	public String showProductDetails(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "renterProductDetails.html";
		String ret = page;

		try {
			if (sessionHandler.isLessorLoggedIn()) {
				ret = "renterProductDetails.html";
			} else {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/renterHomepage.html")
	public String showRenterHomepage(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "renterHomepage.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "renterHomepage.html";
			} else if (!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/registerRentee.html")
	public String registerRentee(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "registerRentee.html";
		String ret = page;

		try {
			if (sessionHandler.isUserLoggedIn()) {
				if (sessionHandler.isLesseeLoggedIn()) {
					ret = "index.html";
				} else if (sessionHandler.isLessorLoggedIn()) {
					ret = "renterHomepage.html";
				}
			} else {
				ret = "registerRentee.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/registerRenter.html")
	public String removeRenter(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "registerRenter.html";
		String ret = page;

		try {
			if (sessionHandler.isUserLoggedIn()) {
				if (sessionHandler.isLesseeLoggedIn()) {
					ret = "index.html";
				} else if (sessionHandler.isLessorLoggedIn()) {
					ret = "renterHomepage.html";
				}
			} else {
				ret = "registerRenter.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@GetMapping("/renterProfile.html")
	public String viewProfileLessor(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "renterProfile.html";
		String ret = page;

		try {
			if (sessionHandler.isLessorLoggedIn()) {
				ret = "renterProfile.html";
			} else if (!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	};

	@GetMapping("/renteeProfile.html")
	public String viewProfileLessee(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "renteeProfile.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "renteeProfile.html";
			} else if (!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	};

	@GetMapping("/cart.html")
	public String showCart(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "cart.html";
		String ret = page;

		try {
			if (sessionHandler.isLessorLoggedIn()) {
				ret = "cart.html";
			} else if(!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	};

	@GetMapping("/payment_checkout.html")
	public String payment(String name, Model model) {
		return "payment_checkout.html";
	};

	@GetMapping("/renteeProductsList.html")
	public String showRenteeProductList(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "renteeProductsList.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "renteeProductsList.html";
			} else if(!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	};

	@GetMapping("/renterProductsList.html")
	public String showRenterProductList(String name, Model model) {
		SessionHandler sessionHandler = AppContext.getSessionHandler();
		String page = "renterProductsList.html";
		String ret = page;

		try {
			if (sessionHandler.isLesseeLoggedIn()) {
				ret = "renterProductsList.html";
			} else if(!sessionHandler.isUserLoggedIn()) {
				ret = "login.html";
			}
			if (!ret.equalsIgnoreCase(page)) {
				sessionHandler.httpRedirect(ret);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	};
}