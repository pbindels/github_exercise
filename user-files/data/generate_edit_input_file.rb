users = File.open("users.csv","r")
files = File.open("edit_files.csv","r")
output = File.open("edit_file_gatling.csv","w")

output.puts("userName,fileName,blobPath,prePath,postPath")
users.each do | u |
  next if u =~ /^userName$/
  u.chomp!
puts u
  files = File.open("edit_files.csv","r")
  files.each do | f |
    next if f =~ /^fileName$/
    f.chomp!
puts f
    output.puts("#{u},#{f},/first-org/docker/blob/master/#{f}?_pjax=%23js-repo-pjax-container,/first-org/docker/edit/master/#{f},/first-org/docker/tree-save/master/#{f}")
  end
end


