FROM grafana/grafana-oss:8.5.2

RUN grafana-cli --pluginsDir "/var/lib/grafana/plugins" plugins install grafana-simple-json-datasource
COPY ./Grafana_Dashboard.json /etc/grafana/provisioning/dashboards/Grafana_Dashboard.json
