import os
import requests
import feedparser
from datetime import datetime, timedelta

def get_github_user_info(username):
    url = f"https://api.github.com/users/{username}"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        return None

def get_recent_blog_posts(feed_url, num_posts=5):
    feed = feedparser.parse(feed_url)
    posts = []
    for entry in feed.entries[:num_posts]:
        posts.append(f"- [{entry.title}]({entry.link})")
    return "\n".join(posts)

def get_recent_activity(username, num_events=5):
    url = f"https://api.github.com/users/{username}/events/public"
    response = requests.get(url)
    if response.status_code == 200:
        events = response.json()
        activity = []
        for event in events[:num_events]:
            if event['type'] == 'PushEvent':
                repo = event['repo']['name']
                commits = event['payload']['commits']
                activity.append(f"🔨 Pushed {len(commits)} commit(s) to {repo}")
            elif event['type'] == 'CreateEvent':
                repo = event['repo']['name']
                ref_type = event['payload']['ref_type']
                activity.append(f"📂 Created {ref_type} in {repo}")
            elif event['type'] == 'IssuesEvent':
                repo = event['repo']['name']
                action = event['payload']['action']
                issue = event['payload']['issue']['title']
                activity.append(f"🐛 {action.capitalize()} issue in {repo}: {issue}")
        return "\n".join(activity)
    else:
        return "Unable to fetch recent activity"

def get_languages_and_tools(username):
    url = f"https://api.github.com/users/{username}/repos"
    response = requests.get(url)
    if response.status_code == 200:
        repos = response.json()
        languages = set()
        for repo in repos:
            if repo['language']:
                languages.add(repo['language'])
        return ", ".join(languages)
    else:
        return "Unable to fetch languages and tools"

def update_readme(username):
    with open('README.md', 'r') as file:
        readme = file.read()

    user_info = get_github_user_info(username)
    if user_info:
        readme = readme.replace('{name}', user_info['name'] or username)
        readme = readme.replace('{username}', username)
        readme = readme.replace('{tagline}', user_info['bio'] or "")

    blog_posts = get_recent_blog_posts(user_info['blog'])
    readme = readme.replace('{blog_posts}', blog_posts)

    recent_activity = get_recent_activity(username)
    readme = readme.replace('{recent_activity}', recent_activity)

    languages_and_tools = get_languages_and_tools(username)
    readme = readme.replace('{languages_and_tools}', languages_and_tools)

    # Replace these with actual usernames or leave them as is if not available
    readme = readme.replace('{linkedin_username}', 'your-linkedin-username')
    readme = readme.replace('{twitter_username}', 'your-twitter-username')

    with open('README.md', 'w') as file:
        file.write(readme)

if __name__ == "__main__":
    github_username = os.environ.get('GITHUB_ACTOR', 'your-github-username')
    update_readme(github_username)