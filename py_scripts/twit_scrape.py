import csv

from searchtweets import ResultStream, gen_rule_payload, load_credentials, collect_results

"""
load Twitter API credentials
"""
premium_search_args = load_credentials("~/.twitter_keys.yaml",
                                       yaml_key="search_tweets_api",
                                       env_overwrite=False)

"""
search for "spectrumtv" and create a dict of tweet timestamp (dictionary key, in epoch seconds), 
                                             tweet authors screen name (dict value, tuple element 1),
                                             tweet text (dict value, tuple element 2)
"""
print("searching for tweets containing \"spectrumtv\"")
spectrumtv_rule = gen_rule_payload("spectrumtv", results_per_call=100)
spectv_rs = ResultStream(rule_payload=spectrumtv_rule,
                         max_results=500,
                         max_pages=1,
                         **premium_search_args)
spectrumtv_results = list(spectv_rs.stream())
spectrumtv_tweets = {}
for tweet in spectrumtv_results:
    spectrumtv_tweets[tweet.created_at_seconds] = (tweet.screen_name,
                                                   tweet.all_text.replace('\n', ' '))
print("{} tweets found containing \"spectrumtv\"\n".format(len(spectrumtv_results)))

# repeat of above logic with a different search param
print("searching for tweets containing \"spectrumoutage\"")
spectrum_outage_rule = gen_rule_payload("spectrumoutage", results_per_call=100)
spec_out_rs = ResultStream(rule_payload=spectrum_outage_rule,
                           max_results=500,
                           max_pages=1,
                           **premium_search_args)
spectrum_outage_results = list(spec_out_rs.stream())
spectrum_outage_tweets = {}
for tweet in spectrum_outage_results:
    spectrum_outage_tweets[tweet.created_at_seconds] = (tweet.screen_name,
                                                        tweet.all_text.replace('\n', ' '))
print("{} tweets found containing \"spectrumoutage\"\n".format(len(spectrum_outage_results)))

# repeat of above logic with a different search param
print("searching for tweets containing \"ask_spectrum\"")
ask_spectrum_rule = gen_rule_payload("ask_spectrum", results_per_call=100)
ask_spec_rs = ResultStream(rule_payload=ask_spectrum_rule,
                           max_results=500,
                           max_pages=1,
                           **premium_search_args)
ask_spectrum_results = list(ask_spec_rs.stream())
ask_spectrum_tweets = {}
for tweet in ask_spectrum_results:
    ask_spectrum_tweets[tweet.created_at_seconds] = (tweet.screen_name,
                                                     tweet.all_text.replace('\n', ' '))
print("{} tweets found containing \"ask_spectrum\"\n".format(len(ask_spectrum_results)))


"""
add all search returned tweets to a single dictionary, with same keys/values as above
    - duplicate tweets between the three dicts should be overwritten as they share the same key 
"""
print("combining all search results into a single dictionary")
all_spectrum_tweets = {**spectrumtv_tweets, **spectrum_outage_tweets, **ask_spectrum_tweets}


"""
write all collected tweets to a comma-separated .csv file
"""
print("writing results to csv: spectrum_tweets.csv")
with open('spectrum_tweets.csv', 'w', newline='', encoding='utf-8') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerows((k,) + v for k, v in all_spectrum_tweets.items())
