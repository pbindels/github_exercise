require 'github_api'

#github = Github.new basic_auth: 'admin1:2a40e3632217646e41e945e420e1891d7c0a0366', site: 'http://58.88.81.144'
github = Github.new basic_auth: 'user1:passworD1', endpoint: 'http://58.88.81.144/api/v3', org: 'first-org', repo: 'docker'
github2 = Github.new oauth_token: '04f3a29ab39d093c861bc6678af31545da11bc06', endpoint: 'http://58.88.81.144/api/v3' 
#github3 = Github.new client_id: 'user1', client_secret: '04f3a29ab39d093c861bc6678af31545da11bc06', site: 'http://58.88.81.144/api/v3' 
#github2.oauth.app.create 'user1', scopes: ['repo']
#repos = Github::Client::Repos.new
actions = github.repos.actions
puts actions

#repos = Github::Client::Repos.new
repos = github.repos.branches 'user1', 'first-org'
#repos.branches user: 'user1', repo: 'first-org/docker' do |branch|
#    puts branch.name
#end

#repos = github.orgs.list
exit

#repos = github.repos.list user: 'user1'

users = github.users

#puts repos.inspect

puts actions

puts users.inspect

puts users.current_options

members = github.orgs

refs = github.git_data.references 'user2', 'docker'
puts refs.inspect
#refs = github.git_data.references.list 'first-org', 'docker'
#
#commits = github.git_data.commits.get 'user1', 'docker', '6bd04a86df26a7bf432dd9d4ecf2183c8fe65e9f'

#refs = github.git_data.refs.get 'user1', 'docker'

