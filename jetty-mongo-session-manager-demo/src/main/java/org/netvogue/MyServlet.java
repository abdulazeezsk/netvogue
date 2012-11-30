package org.netvogue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class MyServlet extends HttpServlet {

  private class CountHolder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer count;
    private Date time;

    public CountHolder() {
        count = 0;
        time = new Date();
    }

    public Integer getCount() {
        return count;
    }

    public void plusPlus() {
        count++;
    }

    public void setTime(final Date time) {
        this.time = time;
    }
}

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();

    CountHolder count;

    if (session.getAttribute("count") != null) {
      count = (CountHolder) session.getAttribute("count");
    } else {
      count = new CountHolder();
    }

    count.setTime(new Date());
    count.plusPlus();

    System.out.println("Count: " + count.getCount());

    session.setAttribute("count", count);

    resp.getWriter().print("count = " + count.getCount());
  }
}
