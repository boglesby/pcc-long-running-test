gfsh \
-e "connect --url=$URL --user=$USERNAME --password=$PASSWORD --skip-ssl-validation" \
-e "set variable --name=APP_RESULT_VIEWER --value=any" \
-e "deploy --jar=build/libs/server-0.0.1-SNAPSHOT.jar" \
-e "list functions"
