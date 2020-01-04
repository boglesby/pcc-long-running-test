gfsh \
-e "connect --url=$URL --user=$USERNAME --password=$PASSWORD --skip-ssl-validation" \
-e "set variable --name=APP_RESULT_VIEWER --value=any" \
-e "undeploy" \
-e "destroy region --name=Trades" \
-e "list functions" \
-e "list regions" \
-e "list indexes"
