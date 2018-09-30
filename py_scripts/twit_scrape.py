import csv

from searchtweets import ResultStream, gen_rule_payload, load_credentials, collect_results


def tweet_search(search_key, search_args):
    """
    search for "spectrumtv" and create a dict of tweet timestamp (dictionary key, in epoch seconds),
                                                 tweet authors screen name (dict value, tuple element 1),
                                                 tweet text (dict value, tuple element 2)
    """
    print("searching for tweets containing \"{}\"".format(search_key))
    key_rule = gen_rule_payload(search_key, results_per_call=100)
    key_rs = ResultStream(rule_payload=key_rule,
                          max_results=500,
                          max_pages=1,
                          **search_args)
    key_results = list(key_rs.stream())
    key_tweets = {}
    for tweet in key_results:
        key_tweets[tweet.created_at_seconds] = (tweet.screen_name,
                                                tweet.all_text.replace('\n', ' '),
                                                ' ')  # this space is a placeholder for the sentiment value
    print("{} tweets found containing \"{}\"\n".format(len(key_results), search_key))
    return key_tweets


"""
load Twitter API credentials
"""
premium_search_args = load_credentials("~/.twitter_keys.yaml",
                                       yaml_key="search_tweets_api",
                                       env_overwrite=False)

"""
run the actual tweet searches
"""
spectrumtv_tweets = tweet_search("spectrumtv", premium_search_args)

spectrum_cable_tweets = tweet_search("spectrumcable", premium_search_args)

spectrum_outage_tweets = tweet_search("spectrumoutage", premium_search_args)

ask_spectrum_tweets = tweet_search("ask_spectrum", premium_search_args)

twc_tweets = tweet_search("timewarnercable", premium_search_args)


"""
add all search returned tweets to a single dictionary, with same keys/values as above
    - duplicate tweets between the three dicts should be overwritten as they share the same key 
"""
print("combining spectrum search results into a single dictionary")
all_spectrum_tweets = {**spectrumtv_tweets, **spectrum_outage_tweets, **ask_spectrum_tweets, **spectrum_cable_tweets}

print("combining all search results into a single dictionary")
all_tweets = {**spectrumtv_tweets,
              **spectrum_outage_tweets,
              **ask_spectrum_tweets,
              **spectrum_cable_tweets,
              **twc_tweets}

"""
write all collected tweets to a comma-separated .csv file
"""
print("writing {} tweets to csv: spectrum_tweets.csv".format(len(all_spectrum_tweets)))
with open('spectrum_tweets.csv', 'w', newline='', encoding='utf-8') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerows((k,) + v for k, v in all_spectrum_tweets.items())

print("writing {} tweets to csv: all_tweets.csv".format(len(all_tweets)))
with open('all_tweets.csv', 'w', newline='', encoding='utf-8') as csvfile:
    csvwriter = csv.writer(csvfile, delimiter=',')
    csvwriter.writerows((k,) + v for k, v in all_tweets.items())
