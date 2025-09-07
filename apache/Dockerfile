FROM httpd:latest
RUN apt-get update && apt-get install -y libapache2-mod-fcgid && rm -rf /var/lib/apt/lists/*
RUN echo "Include conf/extra/httpd.conf" >> /usr/local/apache2/conf/httpd.conf
COPY httpd.conf /usr/local/apache2/conf/extra/httpd.conf
COPY static/ /usr/local/apache2/htdocs/