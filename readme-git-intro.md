# Essential Git Commands Reference Guide

## Repository Setup & Configuration

### Initialize a Repository
```bash
# Create a new Git repository in current directory
git init

# Clone an existing repository
git clone <repository-url>
```

### Configuration
```bash
# Set your identity
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Check your settings
git config --list
```

## Basic Operations

### Tracking Changes
```bash
# Check status of working directory
git status

# Add files to staging area
git add <filename>          # Add specific file
git add .                   # Add all files
git add *.js               # Add all JavaScript files
git add -p                 # Add changes interactively

# Remove files
git rm <filename>          # Remove file from Git and disk
git rm --cached <filename> # Remove file from Git only
```

### Committing
```bash
# Commit staged changes
git commit -m "Your commit message"

# Add and commit in one step (for tracked files)
git commit -am "Your commit message"

# Amend last commit
git commit --amend
```

### Viewing History
```bash
# View commit history
git log                    # Full log
git log --oneline         # Compact log
git log --graph           # Graph view
git log -p                # Show patches
git log -n 5              # Show last 5 commits

# Show changes
git diff                  # Working directory vs staging area
git diff --staged         # Staging area vs last commit
git diff HEAD~1 HEAD      # Between last commit and previous
```

## Branching and Merging

### Branch Management
```bash
# List branches
git branch                # Local branches
git branch -r            # Remote branches
git branch -a            # All branches

# Create branch
git branch <branch-name>
git checkout -b <branch-name>  # Create and switch in one command

# Switch branches
git checkout <branch-name>
git switch <branch-name>       # Modern alternative

# Delete branch
git branch -d <branch-name>    # Safe delete
git branch -D <branch-name>    # Force delete
```

### Merging
```bash
# Merge branch into current branch
git merge <branch-name>

# Abort merge in case of conflicts
git merge --abort
```

## Remote Operations

### Managing Remotes
```bash
# List remotes
git remote -v

# Add remote
git remote add <name> <url>

# Remove remote
git remote remove <name>
```

### Syncing with Remote
```bash
# Download changes
git fetch <remote>
git fetch --all          # From all remotes

# Download and merge changes
git pull <remote> <branch>
git pull origin main

# Upload changes
git push <remote> <branch>
git push origin main
```

## Undoing Changes

### Working Directory
```bash
# Discard changes in working directory
git restore <filename>    # Modern syntax
git checkout -- <filename>  # Old syntax

# Unstage changes
git restore --staged <filename>  # Modern syntax
git reset HEAD <filename>        # Old syntax
```

### Commits
```bash
# Undo last commit but keep changes staged
git reset --soft HEAD^

# Undo last commit and unstage changes
git reset HEAD^

# Undo last commit and discard changes
git reset --hard HEAD^

# Create new commit that undoes previous commit
git revert HEAD
```

## Stashing
```bash
# Save changes for later
git stash

# List stashes
git stash list

# Apply most recent stash
git stash apply

# Apply specific stash
git stash apply stash@{2}

# Remove most recent stash
git stash drop

# Apply and remove most recent stash
git stash pop
```

## Tags
```bash
# Create tag
git tag <tagname>               # Lightweight tag
git tag -a <tagname> -m "message"  # Annotated tag

# List tags
git tag

# Delete tag
git tag -d <tagname>
```

## Tips for Better Git Usage

### Best Practices
1. Write clear, descriptive commit messages
2. Commit early and often
3. Use branches for new features
4. Keep commits focused and atomic
5. Pull before pushing to avoid conflicts

### Useful Aliases
Add these to your Git config:
```bash
[alias]
    st = status
    co = checkout
    br = branch
    ci = commit
    unstage = reset HEAD --
    last = log -1 HEAD
    lg = log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit
```

### Common Workflows
1. Feature Branch Workflow:
   ```bash
   git checkout -b feature/new-feature
   # make changes
   git add .
   git commit -m "Add new feature"
   git checkout main
   git merge feature/new-feature
   ```

2. Hotfix Workflow:
   ```bash
   git checkout -b hotfix/bug-fix
   # fix bug
   git add .
   git commit -m "Fix critical bug"
   git checkout main
   git merge hotfix/bug-fix
   git push origin main
   ```

Remember: Git is a powerful tool, and these are just the basics. As you become more comfortable with these commands, you can explore more advanced features like rebasing, cherry-picking, and reflog.