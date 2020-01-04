gfsh \
-e "connect --url=$URL --user=$USERNAME --password=$PASSWORD --skip-ssl-validation" \
-e "set variable --name=APP_RESULT_VIEWER --value=any" \
-e "export logs --file-size-limit=0"