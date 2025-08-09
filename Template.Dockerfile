FROM devopsfaith/krakend as builder
ARG APP_ENVIRONMENT=$APP_ENVIRONMENT
ENV APP_ENVIRONMENT=$APP_ENVIRONMENT

COPY krakend.tmpl .
COPY config .

RUN pwd
RUN ls -la
RUN cat /etc/krakend/settings/prod/service.json

## Save temporary file to /tmp to avoid permission errors
RUN FC_ENABLE=1 \
    FC_OUT=/tmp/krakend.json \
    FC_PARTIALS="/etc/krakend/partials" \
    FC_SETTINGS="/etc/krakend/settings/${APP_ENVIRONMENT}" \
    FC_TEMPLATES="/etc/krakend/templates" \
    krakend check -ddd -t -c krakend.tmpl

RUN echo "Contenido del archivo krakend.json:" && cat /tmp/krakend.json

# The linting needs the final krakend.json file
RUN krakend check -tnc /tmp/krakend.json

FROM devopsfaith/krakend
COPY --from=builder --chown=krakend:65533 /tmp/krakend.json .

EXPOSE 8080

# Uncomment with Enterprise image:
# COPY LICENSE /etc/krakend/LICENSE
