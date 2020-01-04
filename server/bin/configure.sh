gfsh \
-e "connect --url=$URL --user=$USERNAME --password=$PASSWORD --skip-ssl-validation" \
-e "set variable --name=APP_RESULT_VIEWER --value=any" \
-e "list members" \
-e "deploy --jar=build/libs/server-0.0.1-SNAPSHOT.jar" \
-e "list functions" \
-e "create region --name=Trades --type=PARTITION_REDUNDANT" \
-e "list regions" \
-e "create index --name=cusip_index --expression=cusip --region=/Trades" \
-e "list indexes"
