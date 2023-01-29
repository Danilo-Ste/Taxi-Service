document.querySelectorAll('li > a').forEach((nav) => {
  let startPath = "/com.epam.taxi_service/";
  let index = "/com.epam.taxi_service/index.jsp";
  if (nav.pathname === window.location.pathname ||
      window.location.pathname === startPath && nav.pathname === index) {
    nav.classList.add('active')
  }
})