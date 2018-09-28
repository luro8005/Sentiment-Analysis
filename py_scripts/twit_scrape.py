import csv

from searchtweets import gen_rule_payload, load_credentials, collect_results

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
spectrumtv_rule = gen_rule_payload("spectrumtv", results_per_call=100)

spectrumtv_results = collect_results(spectrumtv_rule,
                                     max_results=100,
                                     result_stream_args=premium_search_args)
spectrumtv_tweets = {}
for tweet in spectrumtv_results:
    spectrumtv_tweets[tweet.created_at_seconds] = (tweet.screen_name,
                                                   tweet.all_text.replace('\n', ' '))


# repeat of above logic with a different search param
spectrum_outage_rule = gen_rule_payload("spectrumoutage", results_per_call=100)

spectrum_outage_results = collect_results(spectrum_outage_rule,
                                          max_results=100,
                                          result_stream_args=premium_search_args)
spectrum_outage_tweets = {}
for tweet in spectrum_outage_results:
    spectrum_outage_tweets[tweet.created_at_seconds] = (tweet.screen_name,
                                                        tweet.all_text.replace('\n', ' '))


# repeat of above logic with a different search param
ask_spectrum_rule = gen_rule_payload("ask_spectrum", results_per_call=100)

ask_spectrum_results = collect_results(ask_spectrum_rule,
                                       max_results=100,
                                       result_stream_args=premium_search_args)
ask_spectrum_tweets = {}
for tweet in ask_spectrum_results:
    ask_spectrum_tweets[tweet.created_at_seconds] = (tweet.screen_name,
                                                     tweet.all_text.replace('\n', ' '))


"""
add all search returned tweets to a single dictionary, with same keys/values as above
    - duplicate tweets between the three dicts should be overwritten as they share the same key 
"""
all_spectrum_tweets = {**spectrumtv_tweets, **spectrum_outage_tweets, **ask_spectrum_tweets}


"""
write all collected tweets to a comma-separated csv file
"""
with open('spectrum_tweets.csv', 'w', newline='', encoding='utf-8') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerows((k,) + v for k, v in all_spectrum_tweets.items())
